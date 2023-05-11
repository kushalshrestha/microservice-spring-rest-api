package com.kushalshrestha.springboot.controller;

import com.kushalshrestha.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/student")
    public Student getStudent() {
        Student student = new Student(1, "Kushal", "Shrestha");
        return student;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Kushal", "Shrestha"));
        students.add(new Student(2, "Lionel", "Messi"));
        students.add(new Student(3, "Cristiano", "Ronaldo"));
        students.add(new Student(4, "Neymar", "Jr."));
        return students;
    }

    @GetMapping("/students/{id}/{firstName}/{lastName}")
    public Student studentPathVariable(@PathVariable("id") int studentId, @PathVariable String firstName, @PathVariable String lastName) {
        return new Student(studentId, firstName, lastName);
    }
}
