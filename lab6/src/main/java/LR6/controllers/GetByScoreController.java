package LR6.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebServiceRef;
import LR6.service.StudentService;
import LR6.entity.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/getByScore")
public class GetByScoreController extends HttpServlet {
    @WebServiceRef
    StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=UTF-8");
        String strScore = req.getParameter("score");
        Double score = Double.parseDouble(strScore.equals("") ? "0" : strScore);
        List<Student> students = studentService.getByScore(score);
        if (students == null) {
            resp.getWriter().write("Студентов у которых балл не ниже " + score + " не найдено :(");
        } else {
            req.setAttribute("students", students);
            req.getRequestDispatcher("students.jsp").forward(req, resp);
        }
    }
}