package com.veer.studentdb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.veer.studentdb.Interface.TeacherService;
import com.veer.studentdb.entity.Course;
import com.veer.studentdb.entity.Teacher;
import com.veer.studentdb.repository.CourseRepository;
import com.veer.studentdb.repository.TeacherRepository;
import com.veer.studentdb.request.TeacherRequest;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Teacherserviceimpl implements TeacherService {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	CourseRepository courseRepository;

	public Teacher addTeacher(TeacherRequest teacherRequest) throws Exception {

		log.info(this.getClass().getSimpleName() + " - addTeacher Request is invoked ");
		Teacher teacher = new Teacher();
		System.out.println(teacher.toString());
		teacher.setTeacherName(teacherRequest.getTeacherName());
		teacher.setTeacherAge(teacherRequest.getTeacherAge());

		Optional<Course> course = courseRepository.findById(teacherRequest.getCourse_id());
		if (!course.isPresent()) {
			throw new Exception("Course id not found");
		} else {
			teacher.setCourse(course.get());
		}

		return teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Optional<Teacher> getTeacherById(int stuid) {
		return teacherRepository.findById(stuid);
	}

	@Override
	public Teacher updateTeacher(Teacher stu) {
		return teacherRepository.save(stu);
	}

	@Override
	public void deleteTeacherById(int stuid) {
		teacherRepository.deleteById(stuid);
	}

	@Override
	public void deleteAllTeachers() {
		teacherRepository.deleteAll();
	}

}
