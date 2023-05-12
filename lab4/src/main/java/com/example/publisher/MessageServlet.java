package com.example.publisher;

import jakarta.annotation.Resource;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.DeliveryMode;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "messageServlet", value = "/messages")
public class MessageServlet extends HttpServlet {

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "sentenceQueue")
    private Queue queue;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("messageSent", null);
        getServletContext().getRequestDispatcher("/messages.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String body = req.getParameter("message");
        if (body != null && !body.equals("")) {
            try {
                Connection connection = connectionFactory.createConnection();
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(queue);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                TextMessage message = session.createTextMessage(body);
                producer.send(message);
            } catch (JMSException e) {
                resp.getWriter().println("Error while trying to send <" + body + "> message: " + e.getMessage());
            }
        }
        req.setAttribute("messageSent", body);
        getServletContext().getRequestDispatcher("/messages.jsp").forward(req, resp);
    }
}