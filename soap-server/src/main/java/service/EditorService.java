package service;

import dto.CategoryDTO;
import exception.CategoryNotFoundException;
import exception.DatabaseModificationException;
import exception.ElementNotFoundException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.LinkedList;

@WebService(targetNamespace = "http://soa.org/soap/editor")
public interface EditorService {

    @WebMethod
    LinkedList<CategoryDTO> getAllCategories();

    @WebMethod
    LinkedList<CategoryDTO> getAllCategoriesForSoapUser();

    @WebMethod
    void addCategory(String name, Integer size)
            throws DatabaseModificationException;

    @WebMethod
    void addElement(Integer idCategory, String name, Integer fortune, Integer property, Integer power)
            throws CategoryNotFoundException, DatabaseModificationException;

    @WebMethod
    void updateElementFortune(Integer idElement, Integer fortune)
            throws ElementNotFoundException, DatabaseModificationException;

    @WebMethod
    void updateElementPower(Integer idElement)
            throws ElementNotFoundException, DatabaseModificationException;

}
