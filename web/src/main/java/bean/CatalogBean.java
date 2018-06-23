package bean;

import bean.manager.PushBean;
import boundary.CharactersServiceRemote;
import model.Category;
import model.Element;
import model.TreeNodeData;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import util.EJBUtility;
import util.MessagesUtility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Named(value = "catalogBean")
@SessionScoped
public class CatalogBean implements Serializable {

    private static final long serialVersionUID = -9217712787886869451L;

    @Inject
    private PushBean pushBean;

    private CharactersServiceRemote charactersServiceRemote;
    private TreeNode root;
    private LinkedList<LinkedList<Element>> bestElements;
    private String errorMessage;

    public CatalogBean() throws NamingException {

        charactersServiceRemote = EJBUtility.lookupCharactersService();
//        pushBean.dummyMethod();
        System.out.println("Catalog charactersServiceRemote constructor end");
    }

    @PostConstruct
    public void init() {
        initDataView();
        initBestElementsList();
        errorMessage = null;
    }

    public void deleteElement(String type, Integer id) {

        try {
            if (type.equals(Category.class.getSimpleName())) {
                charactersServiceRemote.deleteCategory(id);
                errorMessage = null;
            } else if (type.equals(Element.class.getSimpleName())) {
                charactersServiceRemote.deleteElement(id);
                errorMessage = null;
            } else {
                System.out.println("Delete element: Unknown type");
            }
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
        }
    }

    public void initBestElementsList() {
        System.out.println("Initialization of best elements...");
        bestElements = new LinkedList<>();
        bestElements = charactersServiceRemote.getBestElementsForTypeSets();
    }

    public void initDataView() {
        root = new DefaultTreeNode("Characters", null);
        this.getCategories().forEach(
                c -> addNode(
                        root,
                        new TreeNodeData(
                                c.getClass().getSimpleName(),
                                c.getIdCategory(),
                                c.getName(),
                                c.getUser().getTypeSet().getCategoryType(),
                                c.getUser().getUsername(),
                                "size: " + c.getSize()
                        ),
                        charactersServiceRemote.getElementsByIdCategory(c.getIdCategory()),
                        c.getUser().getTypeSet().getElementType(),
                        c.getUser().getUsername()
                ));
    }

    public void updateGrowlAction(ActionEvent actionEvent) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            addMessage(errorMessage);
            System.out.println(errorMessage);
        }
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void addNode(TreeNode parentNode, TreeNodeData data, Collection<Element> elements, String typeSetType,
                         String owner) {
        TreeNode node = new DefaultTreeNode(data, parentNode);
        node.setExpanded(true);
        if (elements != null) {
            for (Element e : elements) {
                addNode(node,
                        new TreeNodeData(
                                e.getClass().getSimpleName(),
                                e.getIdElement(),
                                e.getName(),
                                typeSetType,
                                owner,
                                "fortune: " + e.getFortune() +
                                        ", prop: " + e.getProperty() +
                                        ", power: " + e.getPower()
                        ),
                        null,
                        typeSetType,
                        owner
                );
            }
        }
    }

    private List<Category> getCategories() {
        return new LinkedList<>(charactersServiceRemote.getAllCategories());
    }

    public TreeNode getRoot() {
        return root;
    }

    public LinkedList<LinkedList<Element>> getBestElements() {
        return bestElements;
    }

    public void setBestElements(LinkedList<LinkedList<Element>> bestElements) {
        this.bestElements = bestElements;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
