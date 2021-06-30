package com.veer.studentdb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veer.studentdb.Interface.StudentService;
import com.veer.studentdb.entity.Course;
import com.veer.studentdb.entity.Student;
import com.veer.studentdb.repository.CourseRepository;
import com.veer.studentdb.repository.StudentRepository;
import com.veer.studentdb.request.StudentRequest;

@Service
public class Studentserviceimpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	public Student addStudent(StudentRequest StudentRequest) throws Exception {

		Student student = new Student();
		System.out.println(student.toString());
		student.setStudentName(StudentRequest.getStudentName());
		student.setStudentDepartment(StudentRequest.getStudentDepartment());
		student.setStudentAge(StudentRequest.getStudentAge());

		Optional<Course> course = courseRepository.findById(StudentRequest.getCourse_id());
		if (!course.isPresent()) {
			throw new Exception("Course id not found");
		} else {
			student.setCourse(course.get());
		}

		return studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(int stuid) {
		return studentRepository.findById(stuid);
	}

	@Override
	public Student addNewStudent(Student stu) {
		return studentRepository.save(stu);
	}

	@Override
	public Student updateStudent(Student stu) {
		return studentRepository.save(stu);
	}

	@Override
	public void deleteStudentById(int stuid) {
		studentRepository.deleteById(stuid);
	}

	@Override
	public void deleteAllStudents() {
		studentRepository.deleteAll();
	}
}