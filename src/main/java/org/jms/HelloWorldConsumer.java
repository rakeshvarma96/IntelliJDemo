package org.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloWorldConsumer {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://RakeshVarma:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("P2PQueue");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        Message message = messageConsumer.receive();
        if(message instanceof TextMessage)
            System.out.println("Message Received "+((TextMessage)message).getText());
        else
            System.out.println("Message Received"+message);
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
