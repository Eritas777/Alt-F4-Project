package ru.profileplus.profileplusproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.profileplus.profileplusproject.model.User;
import ru.profileplus.profileplusproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/platform")
@AllArgsConstructor
public class UserController {

    private UserRepository repository;
    private PasswordEncoder encoder;

    @GetMapping("/mainpage")
    public String mainpage() {
        return "Welcome to profileplus";
    }

    @PostMapping("/new-student")
    public String addStudent(@RequestBody User user) {
        user.setRole("STUDENT");
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "Student created";
    }

    @PostMapping("/new-teacher")
    public String addTeacher(@RequestBody User user) {
        user.setRole("TEACHER");
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "Teacher created";
    }

    @PostMapping("/new-admin")
    public String addAdmin(@RequestBody  User user) {
        user.setRole("ADMIN");
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "Admin created";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or (hasAuthority('STUDENT') and #email == authentication.name)")
    @GetMapping("/{email}")
    public Optional<User> showProfile(@PathVariable String email) {
        return repository.findByEmail(email);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/student-list")
    public List<User> showAllStudents() {
        return repository.findByRole("STUDENT");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update-student")
    public String updateStudent(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "Student updated";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-student")
    public String deleteStudent(@RequestBody User user) {
        repository.delete(user);
        return "Student deleted";
    }
}
