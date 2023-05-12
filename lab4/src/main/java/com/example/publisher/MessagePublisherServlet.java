package com.example.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "messageServlet", value = "/messages")
public class MessagePublisherServlet extends HttpServlet {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private static final String QUEUE_NAME = "my_queue";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("messageSent", null);
        getServletContext().getRequestDispatcher("/messages.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String body = req.getParameter("message");
        if (body != null && !body.equals("")) {
            try {
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                channel.basicPublish("", QUEUE_NAME, null, body.getBytes());

                System.out.println("Sent message: " + body);

                channel.close();
                connection.close();
            } catch (Exception e) {
                resp.getWriter().println("Error while trying to send <" + body + "> message: " + e.getMessage());
            }
        }
        req.setAttribute("messageSent", body);
        getServletContext().getRequestDispatcher("/messages.jsp").forward(req, resp);
    }
}