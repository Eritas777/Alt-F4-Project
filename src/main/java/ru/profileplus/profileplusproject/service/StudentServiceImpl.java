package ru.profileplus.profileplusproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.profileplus.profileplusproject.model.Student;
import ru.profileplus.profileplusproject.repository.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository repository;

    @Override
    public List<Student> showAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public String updateStudent(Student student) {
        repository.save(student);
        return "Student updated";
    }

    @Override
    public String deleteStudent(Student student) {
        repository.delete(student);
        return "Student deleted";
    }
}
