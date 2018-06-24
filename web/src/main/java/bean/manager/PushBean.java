package bean.manager;

import boundary.CharactersServiceRemote;
import model.Category;
import model.Element;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class PushBean implements Serializable, MessageListener {

    private static final long serialVersionUID = -7003984328860090511L;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/topic/soaTopic")
    private Topic mainTopic;

    @Resource(mappedName = "java:/jms/topic/soaFractionTopic")
    private Topic fractionTopic;

    @Inject
    @Push(channel = "fractionChannel")
    private PushContext fractionChannel;

    //-------------------------------------------

    @Inject
    @Push(channel = "pushChannel")
    private PushContext pushChannel;

    private List<List<Element>> bestElements;
    private List<Category> categories;

    private CharactersServiceRemote charactersServiceRemote;

    //-------------------------------------------

    public PushBean() {
        System.out.println("Push bean constructor");
        try {
            charactersServiceRemote = EJBUtility.lookupCharactersService();
        } catch (NamingException e) {
            System.err.println("Couldn't lookup characters service in push bean");
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("Push bean post construct");

        updateCategories();
        updateElements();

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer mainTopicConsumer = session.createConsumer(mainTopic);
            MessageConsumer fractionTopicConsumer = session.createConsumer(fractionTopic);
            connection.start();
            mainTopicConsumer.setMessageListener(this);
            fractionTopicConsumer.setMessageListener(this);
        } catch (JMSException e) {
            System.err.println("Couldn't setup JMS");
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            final TextMessage textMessage = (TextMessage) message;
            String messageContent = textMessage.getText();

            switch (messageContent) {
                case "category-add":
                    updateCategories();
                    break;
                case "category-update":
                    updateCategories();
                    break;
                case "category-delete":
                    updateCategories();
                    updateElements();
                    break;
                case "element-add":
                    updateCategories();
                    updateElements();
                    break;
                case "element-update":
                    updateCategories();
                    break;
                case "element-delete":
                    updateCategories();
                    updateElements();
                    break;
                default:
                    System.out.println("New element was added");
                    System.out.println(String.valueOf(textMessage.getObjectProperty("RECEIVER")));
                    fractionChannel.send(
                            textMessage.getText(),
                            String.valueOf(textMessage.getObjectProperty("RECEIVER"))
                    );
            }
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }
    }

    private void updateCategories() {
        categories = new ArrayList<>(charactersServiceRemote.getAllCategories());
        pushChannel.send("categories");
    }

    private void updateElements() {
        bestElements = new ArrayList<>(charactersServiceRemote.getBestElementsForTypeSets());
        pushChannel.send("elements");
    }

    public void dummyMethod() {
        System.out.println("Hello from dummy method!");
    }

    public List<List<Element>> getBestElements() {
        return bestElements;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
