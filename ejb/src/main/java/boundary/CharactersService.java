package boundary;

import cdi.Reductional;
import dao.CategoryDAO;
import dao.ElementDAO;
import dao.TypeSetDAO;
import dao.UserDAO;
import exception.*;
import jms.JMSService;
import model.Category;
import model.Element;
import model.TypeSet;
import model.User;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.LinkedList;
import java.util.List;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@PermitAll
@RunAs("User")
@Remote(CharactersServiceRemote.class)
public class CharactersService implements CharactersServiceRemote {

    @Resource
    private SessionContext sessionContext;

    @Inject
    private JMSService jmsService;

    @EJB
    private UserDAO userDAO;

    @EJB
    private CategoryDAO categoryDAO;

    @EJB
    private ElementDAO elementDAO;

    @EJB
    private TypeSetDAO typeSetDAO;

    // typeSet

    @Override
    public TypeSet getTypeSetByIdCategory(Integer idCategory) {
        return typeSetDAO.findTypeSetByCategoryId(idCategory).orElseThrow(
                () -> new TypeSetNotFoundException("Cannot find type set by id category " + idCategory)
        );
    }

    // users

    @Override
    public LinkedList<User> getAllUsers() {
        return new LinkedList<>(userDAO.findAll());
    }

    @Override
    public User getUserFromSessionWithTypeSet() {
        return userDAO.findByUsernameWithTypeSet(sessionContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new UserNotFoundException("User from session not found"));
    }

    @Override
    public boolean userExists(String username) {
        return userDAO.existsByUsername(username);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        if (hasModificationRights(username)) {
            User userToUpdate = userDAO.findByUsername(username).orElseThrow(
                    () -> new UserNotFoundException("User not found")
            );
            if (newPassword == null || newPassword.isEmpty()) {
                throw new WrongFormatException("Wrong password format");
            } else {
                try {
                    userDAO.update(userToUpdate.getIdUser(), username, newPassword);
                } catch (PersistenceException e) {
                    throw new PersistenceException("Cannot update password for user " + username);
                }
            }
        } else {
            throw new AccessDeniedException("You have no rights to change this password");
        }
    }

    // categories

    @Override
    public LinkedList<Category> getAllCategories() {
        return new LinkedList<>(categoryDAO.findAllWithUserAndTypeSet());
    }

    @Override
    public LinkedList<Category> getAllCategoriesForElement(Integer idElement) {
        TypeSet typeSet = typeSetDAO.findTypeSetByElementId(idElement).orElseThrow(
                () -> new TypeSetNotFoundException("Cannot get typeset for element by id " + idElement)
        );
        return new LinkedList<>(categoryDAO.findAllForElementByIdTypeSet(typeSet.getIdTypeSet()));
    }

    @Override
    public LinkedList<Category> getAllCategoriesWithElements() {
        return new LinkedList<>(categoryDAO.findAllWithElements());
    }

    @Override
    public LinkedList<Category> getCategoriesBySessionUser() {
        User user = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new UserNotFoundException("User logged in not found"));
        if (sessionContext.isCallerInRole("Manager")) {
            System.err.println("Finding all categories for user " + user.getUsername());
            return new LinkedList<>(categoryDAO.findAll());
        }
        return new LinkedList<>(categoryDAO.findAllByUser(user.getIdUser()));
    }

    @Override
    public Category getCategoryByIdCategory(Integer idCategory) {
        return categoryDAO.findById(idCategory).orElseThrow(
                () -> new CategoryNotFoundException("Cannot get category by id " + idCategory)
        );
    }

    @Override
    public void addCategory(String name, Integer size, Integer typeSetId) {
        User userFromSession = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName())
                .orElseThrow(
                        () -> new UserNotFoundException("User logged in not found")
                );
        try {
            categoryDAO.add(new Category(name, size, userFromSession));
            jmsService.sendMessageToAll("category-add");
        } catch (PersistenceException e) {
            throw new PersistenceException("Cannot add category");
        }
    }

    @Override
    public void updateCategory(Integer idCategory, String name, Integer size) {
        Category categoryToUpdate = categoryDAO.findById(idCategory).orElseThrow(
                () -> new CategoryNotFoundException("Cannot find category to update by id " + idCategory)
        );
        if (hasModificationRights(categoryToUpdate.getUser().getUsername())) {
            try {
                categoryDAO.update(idCategory, name, size);
                jmsService.sendMessageToAll("category-update");
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot update category " + idCategory);
            }
        } else {
            throw new AccessDeniedException("You have no rights to update category");
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryDAO.findByIdWithUser(id).orElseThrow(
                () -> new CategoryNotFoundException("Cannot find category to remove by id " + id)
        );
        if (hasModificationRights(category.getUser().getUsername())) {
            try {
                categoryDAO.remove(id);
                jmsService.sendMessageToAll("category-delete");
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot remove category " + id);
            }
        } else {
            throw new AccessDeniedException("You have no rights to delete category");
        }
    }

    // elements

    @Override
    public LinkedList<Element> getElementsByIdCategory(Integer idCategory) {
        return new LinkedList<>(elementDAO.findAllByIdCategory(idCategory));
    }

    @Override
    public Element getElementByIdElement(Integer idElement) {
        return elementDAO.findById(idElement).orElseThrow(
                () -> new ElementNotFoundException("Cannot get element by id " + idElement)
        );
    }

    @Override
    public Element getElementWithCategoryByIdElement(Integer idElement) {
        return elementDAO.findWithCategory(idElement).orElseThrow(
                () -> new ElementNotFoundException("Cannot get element by id " + idElement)
        );
    }

    @Override
    public LinkedList<LinkedList<Element>> getBestElementsForTypeSets() {
        LinkedList<LinkedList<Element>> bestElements = new LinkedList<>();
        List<TypeSet> typeSets = new LinkedList<>(typeSetDAO.findAll());
        typeSets.forEach(typeSet -> bestElements.add(
                new LinkedList<>(elementDAO.findBestElementsByTypeSet(typeSet.getIdTypeSet()))
        ));
        return bestElements;
    }

    @Reductional
    @Override
    public void addElement(Integer idCategory, String name, Integer quantity, Integer propType, Integer power) {
        Category category = categoryDAO.findByIdWithUser(idCategory).orElseThrow(
                () -> new CategoryNotFoundException("Category not found by id " + idCategory)
        );
        User categoryUser = userDAO.findById(category.getUser().getIdUser()).orElseThrow(
                () -> new UserNotFoundException("Category owner doesn't exist")
        );
        if (hasModificationRights(categoryUser.getUsername())) {
            try {
                Category categoryChecked = categoryDAO.findByIdWithUser(category.getIdCategory()).orElseThrow(
                        () -> new CategoryNotFoundException("Cannot find element category  " + category)
                );
                if (!category.getUser().getUsername().equals(categoryChecked.getUser().getUsername())) {
                    throw new AccessDeniedException("You cannot add element to someone else's category");
                }
                elementDAO.add(new Element(category, name, quantity, propType, power));
                Integer idTypeSet = categoryChecked.getUser().getTypeSet().getIdTypeSet();
                String message = "User '" + categoryChecked.getUser().getUsername() +
                        "' from fraction '" + categoryChecked.getUser().getTypeSet().getCategoryType() +
                        "' added new element!";
                jmsService.sendMessageToFraction(message, idTypeSet);
                jmsService.sendMessageToAll("element-add");
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot add element");
            }
        } else {
            throw new AccessDeniedException("You cannot add element to someone else's category");
        }
    }

    @Override
    public void updateElement(Integer idCategory, Integer idElement, String name, Integer fortune, Integer propType, Integer power) {
        Element element = elementDAO.findWithCategoryAndUser(idElement).orElseThrow(
                () -> new ElementNotFoundException("Cannot find element to update by id " + idElement)
        );
        if (hasModificationRights(element.getCategory().getUser().getUsername())) {
            try {
                Category category = categoryDAO.findByIdWithUser(idCategory).orElseThrow(
                        () -> new CategoryNotFoundException("Cannot find element category by id " + idCategory)
                );
                User loggedUser = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName())
                        .orElseThrow(
                                () -> new UserNotFoundException("User logged in not found")
                        );
                if (element.getCategory().getUser().getTypeSet().getIdTypeSet() !=
                        category.getUser().getTypeSet().getIdTypeSet()) {
                    throw new WrongFractionException("Cannot move element to another fraction");
                }
                if (!loggedUser.getUsername().equals(category.getUser().getUsername()) &&
                        !sessionContext.isCallerInRole("Manager")) {
                    throw new AccessDeniedException("You cannot move element to someone else's category");
                }
                elementDAO.update(category, idElement, name, fortune, propType, power);
                jmsService.sendMessageToAll("element-update");
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot update element " + idElement);
            }
        } else {
            throw new AccessDeniedException("You have no rights to update element");
        }
    }

    @Override
    public void deleteElement(Integer id) {
        Element element = elementDAO.findWithCategory(id).orElseThrow(
                () -> new ElementNotFoundException("Cannot find element to remove by id " + id)
        );
        if (hasModificationRights(element.getCategory().getUser().getUsername())) {
            try {
                elementDAO.remove(id);
                jmsService.sendMessageToAll("element-delete");
            } catch (PersistenceException e) {
                throw new PersistenceException("Cannot remove element " + id);
            }
        } else {
            throw new AccessDeniedException("You have no rights to delete element");
        }
    }

    // util

    private boolean hasModificationRights(String username) {

        return isOwnerModifying(username) ||
                sessionContext.isCallerInRole("Manager");
    }

    private boolean isOwnerModifying(String username) {
        User loggedUser = userDAO.findByUsername(sessionContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new UserNotFoundException("User logged in not found"));
        return loggedUser.getUsername().equals(username);
    }
}
