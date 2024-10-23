package ru.profileplus.profileplusproject.service;

import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Student;

import java.util.List;

@Service
public interface StudentService {


    public List<Student> showAllStudents();

    public Student saveStudent(Student student);

    public String updateStudent(Student student);

    public String deleteStudent(Student student);
}
