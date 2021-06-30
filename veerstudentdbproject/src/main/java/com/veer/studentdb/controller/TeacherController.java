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
import com.veer.studentdb.Interface.TeacherService;
import com.veer.studentdb.entity.Teacher;
import com.veer.studentdb.request.TeacherRequest;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher')")
	public List<Teacher> getTeachers() {
		log.info(this.getClass().getSimpleName() + " - Get all Teachers teacherService is invoked.");
		return teacherService.getTeachers();
	}

	@RequestMapping(value = "/{teacherId}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('Admin','Teacher','Student')")
	public Teacher getTeacherById(@PathVariable int teacherId) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Get Teacher details by id is invoked.");
		Optional<Teacher> teach = teacherService.getTeacherById(teacherId);
		if (!teach.isPresent())
			throw new Exception("Could not find Teacher with id- " + teacherId);

		return teach.get();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('Admin')")
	public Teacher createTeacher(@RequestBody TeacherRequest teacherRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Create new Teacher method is invoked.");
		return teacherService.addTeacher(teacherRequest);
	}

	@RequestMapping(value = "/update/{teacherId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('Admin')")
	public Teacher updateTeacher(@RequestBody Teacher updteach, @PathVariable int teacherId) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Update Teacher details by id is invoked.");
		Optional<Teacher> teach = teacherService.getTeacherById(teacherId);
		if (!teach.isPresent())
			throw new Exception("Could not find Teacher with id- " + teacherId);

		if (updteach.getTeacherName() == null || updteach.getTeacherName().isEmpty())
			updteach.setTeacherName(teach.get().getTeacherName());
		if (updteach.getTeacherAge() == 0)
			updteach.setTeacherAge(teach.get().getTeacherAge());
		if (updteach.getCourse() == null)
			updteach.setCourse(teach.get().getCourse());

		updteach.setTeacherId(teacherId);
		return teacherService.updateTeacher(updteach);
	}

	@RequestMapping(value = "/delete/{teacherId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	public void deleteTeacherById(@PathVariable int teacherId) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Delete Teacher by id is invoked.");
		Optional<Teacher> teach = teacherService.getTeacherById(teacherId);
		if (!teach.isPresent())
			throw new Exception("Could not find Teacher with id- " + teacherId);

		teacherService.deleteTeacherById(teacherId);
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('Admin')")
	public void deleteAll() {
		log.info(this.getClass().getSimpleName() + " - Delete all Teachers is invoked.");
		teacherService.deleteAllTeachers();
	}

}
