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
import java.util.LinkedList;

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
    @Push(channel = "fractionChanel")
    private PushContext fractionChanel;

    //-------------------------------------------

    @Inject
    @Push(channel = "pushChanel")
    private PushContext pushChanel;

    private LinkedList<LinkedList<Element>> bestElements;
    private LinkedList<Category> categories;

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

        categories = charactersServiceRemote.getAllCategories();
        bestElements = charactersServiceRemote.getBestElementsForTypeSets();

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
                default:
                    System.out.println("New element was added");
                    System.out.println(String.valueOf(textMessage.getObjectProperty("RECEIVER")));
                    fractionChanel.send(
                            textMessage.getText(),
                            String.valueOf(textMessage.getObjectProperty("RECEIVER"))
                    );
            }
        } catch (JMSException e) {
            System.err.println(e.getMessage());
        }
    }

    public void dummyMethod() {
        System.out.println("Hello from dummy method!");
    }

    public LinkedList<LinkedList<Element>> getBestElements() {
        return bestElements;
    }

    public LinkedList<Category> getCategories() {
        return categories;
    }
}
