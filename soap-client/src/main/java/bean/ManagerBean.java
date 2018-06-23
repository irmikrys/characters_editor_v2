package bean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.soa.soap.editor.CategoryDTO;
import org.soa.soap.editor.EditorService;
import org.soa.soap.editor.EditorService_Service;
import org.soa.soap.editor.ElementDTO;
import util.MessagesUtility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable {

    private static final long serialVersionUID = -7902579108607730057L;

    private EditorService editorService;

    private ArrayList<CategoryDTO> categories;
    private ArrayList<CategoryDTO> categoriesForUser;

    private String categoryName;
    private Integer categorySize;

    private String elemName;
    private Integer elemFortune;
    private Integer elemProperty;
    private Integer elemPower;
    private Integer elemIdCategory;

    private Integer elemIdEdit;
    private Integer elemFortuneEdit;

    private String successMessage;
    private String errorMessage;
    private TreeNode root;

    ManagerBean() {
        System.out.println("Manager bean constructor");
        EditorService_Service editorService_service = new EditorService_Service();
        editorService = editorService_service.getEditorPort();
    }

    @PostConstruct
    public void init() {
        successMessage = null;
        errorMessage = null;
        initCategories();
        initDataView();
        clearFields();
    }

    public void addCategory() {
        try {
            editorService.addCategory(categoryName, categorySize);
            successMessage = "Category successfully added!";
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            successMessage = null;
        }
        clearFields();
        initCategories();
        initDataView();
    }

    public void addElement() {
        try {
            editorService.addElement(elemIdCategory, elemName, elemFortune, elemProperty, elemPower);
            successMessage = "Element successfully added!";
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            successMessage = null;
        }
        clearFields();
        initCategories();
        initDataView();
    }

    public void editElement() {
        try {
            editorService.updateElementFortune(elemIdEdit, elemFortuneEdit);
            successMessage = "Element successfully updated!";
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            successMessage = null;
        }
        clearFields();
        initCategories();
        initDataView();
    }

    public void updateElementPower() {
        try {
            System.out.println("Updating power in 5 seconds...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Trying to update power after 5 seconds...");
            editorService.updateElementPower(elemIdEdit);
            successMessage = "Element randomly updated!";
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
            successMessage = null;
        }
        clearFields();
        elemIdEdit = null;
        initCategories();
        initDataView();
    }

    public void updateGrowlAction(ActionEvent actionEvent) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            addMessage(errorMessage);
            System.out.println(errorMessage);
        }
        if (successMessage != null && !successMessage.isEmpty()) {
            addMessage(successMessage);
            System.out.println(successMessage);
        }
    }

    private void initCategories() {
        System.out.println("Initializing categories...");
        categories = new ArrayList<>(editorService.getAllCategories());
        categoriesForUser = new ArrayList<>(editorService.getAllCategoriesForSoapUser());
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void initDataView() {
        System.out.println("Initializing data view...");
        root = new DefaultTreeNode("Categories", null);
        this.getCategories().forEach(categoryDTO -> {
            Collection<ElementDTO> elements = null;
            if (categoryDTO.getElementDTOS().size() > 0) elements = categoryDTO.getElementDTOS();
            addNode(root,
                    categoryDTO.getName() + " (" + categoryDTO.getIdCategory()
                            + "), size: " + categoryDTO.getSize(),
                    elements
            );
        });
    }

    private void addNode(TreeNode parentNode, String data, Collection<ElementDTO> elements) {
        TreeNode node = new DefaultTreeNode(data, parentNode);
        node.setExpanded(true);
        if (elements != null) {
            for (ElementDTO e : elements) {
                addNode(node,
                        e.getName() + " (" + e.getIdElement() + "), " + e.getFortune() +
                                ", power: " + e.getPower(),
                        null
                );
            }
        }
    }

    private void clearFields() {
        categoryName = null;
        categorySize = null;
        elemFortune = null;
        elemIdCategory = null;
        elemPower = null;
        elemProperty = null;
        elemName = null;
        elemFortuneEdit = null;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryDTO> categories) {
        this.categories = categories;
    }

    public ArrayList<CategoryDTO> getCategoriesForUser() {
        return categoriesForUser;
    }

    public void setCategoriesForUser(ArrayList<CategoryDTO> categoriesForUser) {
        this.categoriesForUser = categoriesForUser;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategorySize() {
        return categorySize;
    }

    public void setCategorySize(Integer categorySize) {
        this.categorySize = categorySize;
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }

    public Integer getElemFortune() {
        return elemFortune;
    }

    public void setElemFortune(Integer elemFortune) {
        this.elemFortune = elemFortune;
    }

    public Integer getElemProperty() {
        return elemProperty;
    }

    public void setElemProperty(Integer elemProperty) {
        this.elemProperty = elemProperty;
    }

    public Integer getElemPower() {
        return elemPower;
    }

    public void setElemPower(Integer elemPower) {
        this.elemPower = elemPower;
    }

    public Integer getElemIdCategory() {
        return elemIdCategory;
    }

    public void setElemIdCategory(Integer elemIdCategory) {
        this.elemIdCategory = elemIdCategory;
    }

    public Integer getElemIdEdit() {
        return elemIdEdit;
    }

    public void setElemIdEdit(Integer elemIdEdit) {
        this.elemIdEdit = elemIdEdit;
    }

    public Integer getElemFortuneEdit() {
        return elemFortuneEdit;
    }

    public void setElemFortuneEdit(Integer elemFortuneEdit) {
        this.elemFortuneEdit = elemFortuneEdit;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TreeNode getRoot() {
        return root;
    }
}
