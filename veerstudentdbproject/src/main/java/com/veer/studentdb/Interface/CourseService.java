package com.veer.studentdb.Interface;

import java.util.List;
import java.util.Optional;

import com.veer.studentdb.entity.Course;
import com.veer.studentdb.request.CourseRequest;

public interface CourseService {

	public Course addCourse(CourseRequest courseRequest);

	public List<Course> getCourses();

	public Optional<Course> getCourseById(int courid);

	public Course updateCourse(Course cour);

	public void deleteCourseById(int courid);

	public void deleteAllCourses();

}
