package jms;

import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;

@ApplicationScoped
public class JMSService {

    private static final Logger logger = Logger.getLogger(JMSService.class);

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/topic/soaTopic")
    private Topic topic;

    @Resource(mappedName = "java:/jms/topic/soaFractionTopic")
    private Topic fractionTopic;

    @Resource(mappedName = "java:/jms/queue/soaQueue")
    private Queue queue;

    private Connection connection;


    private void sendMessage(String msg, Destination destination, String receiver) {
        try {
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            connection.start();

            TextMessage message = session.createTextMessage();
            message.setObjectProperty("RECEIVER", receiver);
            message.setText(msg);

            messageProducer.send(message);
            logger.info("Message with receiver: '" + receiver + "' send!");
        } catch (JMSException e) {
            logger.error("Sending error: '" + e.getMessage() + "'");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (JMSException e) {
                    logger.error("Closing  error: '" + e.getMessage() + "'");
                }
            }
        }
    }

    public void sendMessageToAll(String msg) {
        sendMessage(msg, topic, "");
    }

    public void sendMessageToFraction(String msg, Integer idFraction) {
        sendMessage(msg, fractionTopic, String.valueOf(idFraction));
    }

    public void sendMessageToUser(String msg, String username) {
        sendMessage(msg, queue, username);
    }

}
