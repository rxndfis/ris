package com.example.publisher;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.concurrent.atomic.AtomicInteger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "jakarta.jms.Topic"),
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "sentenceTopic")
}
)
public class MyMessageListener implements MessageListener {

    // 12. Получатель выводит количество сообщений, содержащих $$.

    private final AtomicInteger messagesWithDollarsCounter = new AtomicInteger(0);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("Message received: " + textMessage.getText());
            catchDollarMessage(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        // TODO: 3/15/2023 Use queue, 3 сервиса (консольных приложения) RabbitMQ
        // 1. Web Queue
        // 2. Recieve & post Queue
        // 3. Recieve
    }

    private void catchDollarMessage(String messageText) {
        if (messageText.contains("$$")) {
            System.out.println("Caught message with $$, current counter: " + messagesWithDollarsCounter.incrementAndGet());
        }
    }
}