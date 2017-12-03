package org.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloWorldProducer {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://RakeshVarma:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("P2PQueue");
        MessageProducer messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        String text= "Hello Message From:"+Thread.currentThread().getClass()+" ";
        TextMessage message = session.createTextMessage(text);
        messageProducer.send(message);
        System.out.println("Producer has sent:"+message.getText());
        messageProducer.close();
        session.close();
        connection.close();
        }
}
