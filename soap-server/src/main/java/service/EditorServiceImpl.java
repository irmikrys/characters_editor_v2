package service;

import dao.CategoryDAO;
import dao.ElementDAO;
import dto.CategoryDTO;
import exception.CategoryNotFoundException;
import exception.DatabaseModificationException;
import exception.ElementNotFoundException;
import model.Category;
import model.Element;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.PersistenceException;
import java.util.LinkedList;
import java.util.Random;

@WebService(
        name = "editor",
        portName = "editorPort",
        serviceName = "EditorService",
        endpointInterface = "service.EditorService",
        targetNamespace = "http://soa.org/soap/editor"
)
public class EditorServiceImpl implements EditorService {

    private static final Integer ID_USER = 1;

    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ElementDAO elementDAO;

    @Override
    public LinkedList<CategoryDTO> getAllCategories() {
        return new LinkedList<>(categoryDAO.findAll());
    }

    @Override
    public LinkedList<CategoryDTO> getAllCategoriesForSoapUser() {
        return new LinkedList<>(categoryDAO.findAllByIdUser(ID_USER));
    }

    @Override
    public void addCategory(String name, Integer size)
            throws DatabaseModificationException {
        try {
            categoryDAO.add(new Category(name, size, ID_USER));
        } catch (PersistenceException e) {
            throw new DatabaseModificationException("Cannot add category");
        }
    }

    @Override
    public void addElement(Integer idCategory, String name, Integer fortune, Integer property, Integer power)
            throws CategoryNotFoundException, DatabaseModificationException {
        Category category = categoryDAO.findById(idCategory).orElseThrow(
                () -> new CategoryNotFoundException("Cannot find category by id " + idCategory)
        );
        try {
            elementDAO.add(new Element(name, fortune, property, power, category));
        } catch (PersistenceException e) {
            throw new DatabaseModificationException("Cannot add element");
        }

    }

    @Override
    public void updateElementFortune(Integer idElement, Integer fortune)
            throws ElementNotFoundException, DatabaseModificationException {
        try {
            Element elementToUpdate = elementDAO.findById(idElement).orElseThrow(
                    () -> new ElementNotFoundException("Cannot find element by id " + idElement)
            );
            elementDAO.update(idElement, fortune, elementToUpdate.getPower());
        } catch (PersistenceException e) {
            throw new DatabaseModificationException("Cannot update element fortune");
        }
    }

    @Override
    public void updateElementPower(Integer idElement)
            throws ElementNotFoundException, DatabaseModificationException {
        try {
            Element elementToUpdate = elementDAO.findById(idElement).orElseThrow(
                    () -> new ElementNotFoundException("Cannot find element by id " + idElement)
            );
            Integer newPower = elementToUpdate.getPower() + randomPowerInBetween();
            elementDAO.update(idElement, elementToUpdate.getFortune(), newPower);
        } catch (PersistenceException e) {
            throw new DatabaseModificationException("Cannot update element power");
        }
    }

    private Integer randomPowerInBetween() {
        int low = -2;
        int high = 2;
        Random rand = new Random();
        return rand.nextInt((high - low) + 1) + low;
    }
}
