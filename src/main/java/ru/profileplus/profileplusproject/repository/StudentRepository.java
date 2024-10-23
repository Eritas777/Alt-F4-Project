package ru.profileplus.profileplusproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profileplus.profileplusproject.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
