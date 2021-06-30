package com.veer.studentdb.Interface;

import java.util.List;
import java.util.Optional;

import com.veer.studentdb.entity.Teacher;
import com.veer.studentdb.request.TeacherRequest;

public interface TeacherService {

	public Teacher addTeacher(TeacherRequest teacherRequest) throws Exception;

	public List<Teacher> getTeachers();

	public Optional<Teacher> getTeacherById(int teachid);

	public Teacher updateTeacher(Teacher teach);

	public void deleteTeacherById(int teachid);

	public void deleteAllTeachers();

}
