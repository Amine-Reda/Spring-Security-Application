package com.example.demo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	private  static final List<Student> Students=Arrays.asList(
			new Student(1, "Amine Reda"),
			new Student(2, "Hajar Halouani"),
			new Student(3, "Youssef Mazgouri")
			);
	
	@GetMapping(path = "{studentId}")
public Student getStudent(@PathVariable Integer studentId) {
		
return Students.stream().filter(student->studentId.equals(student.getStudentId()))
		.findFirst()
		.orElseThrow(()->new IllegalStateException("Student "+ studentId +" does not exist"));
}
}
