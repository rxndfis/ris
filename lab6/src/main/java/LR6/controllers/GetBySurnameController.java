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

@WebServlet("/getBySurname")
public class GetBySurnameController extends HttpServlet {
    @WebServiceRef
    StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=UTF-8");
        String surname = req.getParameter("surname");
        List<Student> students = studentService.getBySurname(surname);
        if (students == null) {
            resp.getWriter().write("Студенты с такой фамилией не найдены :(");
        } else {
            req.setAttribute("students", students);
            req.getRequestDispatcher("students.jsp").forward(req, resp);
        }
    }
}