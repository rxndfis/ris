package LR6.service;

import jakarta.jws.WebService;
import LR6.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "LR6.service.StudentService")
public class StudentImplService implements StudentService {
    private final List<Student> students;
    private long id;

    {
        students = new ArrayList<>();
        students.add(new Student(++id, "Ольхов", "Виталя", 8.69));
        students.add(new Student(++id, "Подольская", "Елизавета", 10.0));
        students.add(new Student(++id, "Адамонис", "Сашка", 9.5));
        students.add(new Student(++id, "Игнатович", "Илюха", 9.25));
        students.add(new Student(++id, "Стремоус", "Матыч", 9.44));
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public List<Student> getBySurname(String surname) {
        return students
                .stream()
                .filter(student -> student.getSurname().toLowerCase().contains(surname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getByScore(Double score) {
        return students
                .stream()
                .filter(student -> student.getScore() >= score)
                .collect(Collectors.toList());
    }
}