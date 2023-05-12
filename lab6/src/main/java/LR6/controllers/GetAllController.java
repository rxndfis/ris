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

@WebServlet("/getAll")
public class GetAllController extends HttpServlet {
    @WebServiceRef
    StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Student> students = studentService.getAllStudents();
        if (students == null) {
            resp.getWriter().write("А студентов-то нет :0");
        } else {
            req.setAttribute("students", students);
            req.getRequestDispatcher("students.jsp").forward(req, resp);
        }
    }
}