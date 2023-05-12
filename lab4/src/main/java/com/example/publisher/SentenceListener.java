package com.example.publisher;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author andrey.demyanchik on 2/19/2023
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "sentenceQueue")
}
)
public class SentenceListener implements MessageListener {

    private static final File FILE = new File("D:\\Третий курс\\6 сем\\RIS\\lab4\\publisher\\messages.txt");
    private static final boolean APPEND = true;

    // 3. Получатель записывает в текстовый файл сообщения с восклицательным знаком.
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("Message received: " + textMessage.getText());
            if (textMessage.getText().contains("!")) {
                try (FileWriter fileWriter = new FileWriter(FILE, APPEND);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                     PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
                    printWriter.println(textMessage.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
