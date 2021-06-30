package com.veer.studentdb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.veer.studentdb.Interface.StudentService;
import com.veer.studentdb.entity.Student;
import com.veer.studentdb.request.StudentRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasRole('Admin')")
	public List<Student> getStudents() {
		log.info(this.getClass().getSimpleName() +" - Get all students studentService is invoked ");
		return studentService.getStudents();
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Student')")
	public Student getStudentById(@PathVariable int studentId) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get student details by id is invoked.");
		log.info(this.getClass().getSimpleName() +" - Get student details by id is invoked");
		Optional<Student> stu = studentService.getStudentById(studentId);
		if (!stu.isPresent())
			throw new Exception("Could not find student with id- " + studentId);

		return stu.get();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('Admin')")
	public Student createStudent(@RequestBody StudentRequest studentRequest) throws Exception {
		log.info(this.getClass().getSimpleName() +" - Create new Student method is invoked ");
		return studentService.addStudent(studentRequest);
	}

	@RequestMapping(value = "/update/{studentId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('Admin')")
	public Student updateStudent(@RequestBody Student updstu, @PathVariable int studentId) throws Exception {
	
		log.info(this.getClass().getSimpleName() +" - Update student details by id is invoked ");
		Optional<Student> stu = studentService.getStudentById(studentId);
		if (!stu.isPresent())
			throw new Exception("Could not find student with id- " + studentId);

		if (updstu.getStudentName() == null || updstu.getStudentName().isEmpty())
			updstu.setStudentName(stu.get().getStudentName());

		if (updstu.getStudentDepartment() == null || updstu.getStudentDepartment().isEmpty())
			updstu.setStudentDepartment(stu.get().getStudentDepartment());

		if (updstu.getStudentAge() == 0)
			updstu.setStudentAge(stu.get().getStudentAge());
		if (updstu.getCourse() == null)
			updstu.setCourse(stu.get().getCourse());

		updstu.setStudentId(studentId);
		return studentService.updateStudent(updstu);
	}

	@RequestMapping(value = "/delete/{studentId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	public void deleteStudentById(@PathVariable int studentId) throws Exception {
		log.info(this.getClass().getSimpleName() +" - Delete student by id is invoked ");
		Optional<Student> stu = studentService.getStudentById(studentId);
		if (!stu.isPresent())
			throw new Exception("Could not find student with id- " + studentId);

		studentService.deleteStudentById(studentId);
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	public void deleteAll() {
		log.info(this.getClass().getSimpleName() +" - Delete all students is invoked");
		studentService.deleteAllStudents();
	}
}