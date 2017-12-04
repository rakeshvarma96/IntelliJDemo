package org.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PubSubProducer {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://RakeshVarma:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,TopicSession.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("Testing");
        MessageProducer messageProducer = session.createProducer(topic);
        //messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        String text = "Text From Producer";
        Message message = session.createTextMessage(text);
        messageProducer.send(message);
        messageProducer.close();
        session.close();
        connection.close();
    }
}
