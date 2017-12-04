package org.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PubSubSubscriber2 {
    public static void main(String[] args) throws  Exception{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://RakeshVarma:61616");
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("Rakesh");
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Topic topic = session.createTopic("Testing");
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic,"Test");
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage)
                        System.out.println("Text Message" + ((TextMessage) message).getText());
                    message.acknowledge();
                } catch (JMSException jmse) {
                    jmse.printStackTrace();
                }
            }
        };
        durableSubscriber.setMessageListener(listener);
    }
}
