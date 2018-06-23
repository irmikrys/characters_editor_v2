
package org.soa.soap.editor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.soa.soap.editor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddCategory_QNAME = new QName("http://soa.org/soap/editor", "addCategory");
    private final static QName _AddCategoryResponse_QNAME = new QName("http://soa.org/soap/editor", "addCategoryResponse");
    private final static QName _AddElement_QNAME = new QName("http://soa.org/soap/editor", "addElement");
    private final static QName _AddElementResponse_QNAME = new QName("http://soa.org/soap/editor", "addElementResponse");
    private final static QName _CategoryDTO_QNAME = new QName("http://soa.org/soap/editor", "categoryDTO");
    private final static QName _ElementDTO_QNAME = new QName("http://soa.org/soap/editor", "elementDTO");
    private final static QName _GetAllCategories_QNAME = new QName("http://soa.org/soap/editor", "getAllCategories");
    private final static QName _GetAllCategoriesForSoapUser_QNAME = new QName("http://soa.org/soap/editor", "getAllCategoriesForSoapUser");
    private final static QName _GetAllCategoriesForSoapUserResponse_QNAME = new QName("http://soa.org/soap/editor", "getAllCategoriesForSoapUserResponse");
    private final static QName _GetAllCategoriesResponse_QNAME = new QName("http://soa.org/soap/editor", "getAllCategoriesResponse");
    private final static QName _UpdateElementFortune_QNAME = new QName("http://soa.org/soap/editor", "updateElementFortune");
    private final static QName _UpdateElementFortuneResponse_QNAME = new QName("http://soa.org/soap/editor", "updateElementFortuneResponse");
    private final static QName _UpdateElementPower_QNAME = new QName("http://soa.org/soap/editor", "updateElementPower");
    private final static QName _UpdateElementPowerResponse_QNAME = new QName("http://soa.org/soap/editor", "updateElementPowerResponse");
    private final static QName _DatabaseModificationException_QNAME = new QName("http://soa.org/soap/editor", "DatabaseModificationException");
    private final static QName _ElementNotFoundException_QNAME = new QName("http://soa.org/soap/editor", "ElementNotFoundException");
    private final static QName _CategoryNotFoundException_QNAME = new QName("http://soa.org/soap/editor", "CategoryNotFoundException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.soa.soap.editor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCategory }
     * 
     */
    public AddCategory createAddCategory() {
        return new AddCategory();
    }

    /**
     * Create an instance of {@link AddCategoryResponse }
     * 
     */
    public AddCategoryResponse createAddCategoryResponse() {
        return new AddCategoryResponse();
    }

    /**
     * Create an instance of {@link AddElement }
     * 
     */
    public AddElement createAddElement() {
        return new AddElement();
    }

    /**
     * Create an instance of {@link AddElementResponse }
     * 
     */
    public AddElementResponse createAddElementResponse() {
        return new AddElementResponse();
    }

    /**
     * Create an instance of {@link CategoryDTO }
     * 
     */
    public CategoryDTO createCategoryDTO() {
        return new CategoryDTO();
    }

    /**
     * Create an instance of {@link ElementDTO }
     * 
     */
    public ElementDTO createElementDTO() {
        return new ElementDTO();
    }

    /**
     * Create an instance of {@link GetAllCategories }
     * 
     */
    public GetAllCategories createGetAllCategories() {
        return new GetAllCategories();
    }

    /**
     * Create an instance of {@link GetAllCategoriesForSoapUser }
     * 
     */
    public GetAllCategoriesForSoapUser createGetAllCategoriesForSoapUser() {
        return new GetAllCategoriesForSoapUser();
    }

    /**
     * Create an instance of {@link GetAllCategoriesForSoapUserResponse }
     * 
     */
    public GetAllCategoriesForSoapUserResponse createGetAllCategoriesForSoapUserResponse() {
        return new GetAllCategoriesForSoapUserResponse();
    }

    /**
     * Create an instance of {@link GetAllCategoriesResponse }
     * 
     */
    public GetAllCategoriesResponse createGetAllCategoriesResponse() {
        return new GetAllCategoriesResponse();
    }

    /**
     * Create an instance of {@link UpdateElementFortune }
     * 
     */
    public UpdateElementFortune createUpdateElementFortune() {
        return new UpdateElementFortune();
    }

    /**
     * Create an instance of {@link UpdateElementFortuneResponse }
     * 
     */
    public UpdateElementFortuneResponse createUpdateElementFortuneResponse() {
        return new UpdateElementFortuneResponse();
    }

    /**
     * Create an instance of {@link UpdateElementPower }
     * 
     */
    public UpdateElementPower createUpdateElementPower() {
        return new UpdateElementPower();
    }

    /**
     * Create an instance of {@link UpdateElementPowerResponse }
     * 
     */
    public UpdateElementPowerResponse createUpdateElementPowerResponse() {
        return new UpdateElementPowerResponse();
    }

    /**
     * Create an instance of {@link DatabaseModificationException }
     * 
     */
    public DatabaseModificationException createDatabaseModificationException() {
        return new DatabaseModificationException();
    }

    /**
     * Create an instance of {@link ElementNotFoundException }
     * 
     */
    public ElementNotFoundException createElementNotFoundException() {
        return new ElementNotFoundException();
    }

    /**
     * Create an instance of {@link CategoryNotFoundException }
     * 
     */
    public CategoryNotFoundException createCategoryNotFoundException() {
        return new CategoryNotFoundException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCategory }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCategory }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "addCategory")
    public JAXBElement<AddCategory> createAddCategory(AddCategory value) {
        return new JAXBElement<AddCategory>(_AddCategory_QNAME, AddCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCategoryResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddCategoryResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "addCategoryResponse")
    public JAXBElement<AddCategoryResponse> createAddCategoryResponse(AddCategoryResponse value) {
        return new JAXBElement<AddCategoryResponse>(_AddCategoryResponse_QNAME, AddCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddElement }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddElement }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "addElement")
    public JAXBElement<AddElement> createAddElement(AddElement value) {
        return new JAXBElement<AddElement>(_AddElement_QNAME, AddElement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddElementResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddElementResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "addElementResponse")
    public JAXBElement<AddElementResponse> createAddElementResponse(AddElementResponse value) {
        return new JAXBElement<AddElementResponse>(_AddElementResponse_QNAME, AddElementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoryDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "categoryDTO")
    public JAXBElement<CategoryDTO> createCategoryDTO(CategoryDTO value) {
        return new JAXBElement<CategoryDTO>(_CategoryDTO_QNAME, CategoryDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElementDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElementDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "elementDTO")
    public JAXBElement<ElementDTO> createElementDTO(ElementDTO value) {
        return new JAXBElement<ElementDTO>(_ElementDTO_QNAME, ElementDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCategories }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCategories }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "getAllCategories")
    public JAXBElement<GetAllCategories> createGetAllCategories(GetAllCategories value) {
        return new JAXBElement<GetAllCategories>(_GetAllCategories_QNAME, GetAllCategories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesForSoapUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesForSoapUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "getAllCategoriesForSoapUser")
    public JAXBElement<GetAllCategoriesForSoapUser> createGetAllCategoriesForSoapUser(GetAllCategoriesForSoapUser value) {
        return new JAXBElement<GetAllCategoriesForSoapUser>(_GetAllCategoriesForSoapUser_QNAME, GetAllCategoriesForSoapUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesForSoapUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesForSoapUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "getAllCategoriesForSoapUserResponse")
    public JAXBElement<GetAllCategoriesForSoapUserResponse> createGetAllCategoriesForSoapUserResponse(GetAllCategoriesForSoapUserResponse value) {
        return new JAXBElement<GetAllCategoriesForSoapUserResponse>(_GetAllCategoriesForSoapUserResponse_QNAME, GetAllCategoriesForSoapUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllCategoriesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "getAllCategoriesResponse")
    public JAXBElement<GetAllCategoriesResponse> createGetAllCategoriesResponse(GetAllCategoriesResponse value) {
        return new JAXBElement<GetAllCategoriesResponse>(_GetAllCategoriesResponse_QNAME, GetAllCategoriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateElementFortune }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateElementFortune }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "updateElementFortune")
    public JAXBElement<UpdateElementFortune> createUpdateElementFortune(UpdateElementFortune value) {
        return new JAXBElement<UpdateElementFortune>(_UpdateElementFortune_QNAME, UpdateElementFortune.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateElementFortuneResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateElementFortuneResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "updateElementFortuneResponse")
    public JAXBElement<UpdateElementFortuneResponse> createUpdateElementFortuneResponse(UpdateElementFortuneResponse value) {
        return new JAXBElement<UpdateElementFortuneResponse>(_UpdateElementFortuneResponse_QNAME, UpdateElementFortuneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateElementPower }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateElementPower }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "updateElementPower")
    public JAXBElement<UpdateElementPower> createUpdateElementPower(UpdateElementPower value) {
        return new JAXBElement<UpdateElementPower>(_UpdateElementPower_QNAME, UpdateElementPower.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateElementPowerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateElementPowerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "updateElementPowerResponse")
    public JAXBElement<UpdateElementPowerResponse> createUpdateElementPowerResponse(UpdateElementPowerResponse value) {
        return new JAXBElement<UpdateElementPowerResponse>(_UpdateElementPowerResponse_QNAME, UpdateElementPowerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseModificationException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatabaseModificationException }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "DatabaseModificationException")
    public JAXBElement<DatabaseModificationException> createDatabaseModificationException(DatabaseModificationException value) {
        return new JAXBElement<DatabaseModificationException>(_DatabaseModificationException_QNAME, DatabaseModificationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElementNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ElementNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "ElementNotFoundException")
    public JAXBElement<ElementNotFoundException> createElementNotFoundException(ElementNotFoundException value) {
        return new JAXBElement<ElementNotFoundException>(_ElementNotFoundException_QNAME, ElementNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoryNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://soa.org/soap/editor", name = "CategoryNotFoundException")
    public JAXBElement<CategoryNotFoundException> createCategoryNotFoundException(CategoryNotFoundException value) {
        return new JAXBElement<CategoryNotFoundException>(_CategoryNotFoundException_QNAME, CategoryNotFoundException.class, null, value);
    }

}
