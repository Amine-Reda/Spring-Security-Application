package com.example.demo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementContoller {
	private  static final List<Student> Students=Arrays.asList(
			new Student(1, "Amine Reda"),
			new Student(2, "Hajar Halouani"),
			new Student(3, "Youssef Mazgouri")
			);
	
	@GetMapping
	public List<Student> getAllStudent(){
		System.out.println("getAllStudent");
		return Students;
	}
	
	@PostMapping
	public void registerNewStudent( @RequestBody Student student) {
		System.out.println("registerNewStudent");
		System.out.println(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deletStudent ( @PathVariable("studentId") Integer studentId) {
		System.out.println("deletStudent");
         System.out.println(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student) {
		System.out.println("updateStudent");
		System.out.println(String.format("%s %s",studentId,student));
	}

}
