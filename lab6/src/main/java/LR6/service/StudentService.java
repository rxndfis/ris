package LR6.service;

import jakarta.jws.WebService;
import LR6.entity.Student;

import java.util.List;

@WebService
public interface StudentService {
    List<Student> getAllStudents();
    List<Student> getBySurname(String surname);
    List<Student> getByScore(Double score);
}
