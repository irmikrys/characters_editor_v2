package boundary;

import model.Category;
import model.Element;
import model.TypeSet;
import model.User;

import java.util.LinkedList;

public interface CharactersServiceRemote {

    TypeSet getTypeSetByIdCategory(Integer idCategory);

    LinkedList<User> getAllUsers();

    User getUserFromSessionWithTypeSet();

    boolean userExists(String username);

    void updatePassword(String username, String newPassword);

    LinkedList<Category> getAllCategories();

    LinkedList<Category> getCategoriesBySessionUser();

    LinkedList<Category> getAllCategoriesForElement(Integer idElement);

    LinkedList<Category> getAllCategoriesWithElements();

    Category getCategoryByIdCategory(Integer idCategory);

    void addCategory(String name, Integer size, Integer typeSetId);

    void updateCategory(Integer idCategory, String name, Integer size);

    void deleteCategory(Integer id);

    Element getElementByIdElement(Integer idElement);

    Element getElementWithCategoryByIdElement(Integer idElement);

    LinkedList<Element> getElementsByIdCategory(Integer idCategory);

    LinkedList<LinkedList<Element>> getBestElementsForTypeSets();

    void addElement(Integer idCategory, String name, Integer quantity, Integer propType, Integer power);

    void updateElement(Integer idCategory, Integer idElement, String name, Integer fortune, Integer propType, Integer power);

    void deleteElement(Integer id);
}
