package ru.profileplus.profileplusproject.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.Student;
import ru.profileplus.profileplusproject.service.StudentServiceImpl;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentController {

	private final StudentServiceImpl service;

	@GetMapping
	public List<Student> showAllStudents() {
		return service.showAllStudents();
	}

	@PostMapping("save")
	public Student saveStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}

	@PutMapping("update")
	public String updateStudent(@RequestBody Student student) {
		return service.updateStudent(student);
	}

	@DeleteMapping("delete")
	public String deleteStudent(@RequestBody Student student) {
		return service.deleteStudent(student);
	}
}
