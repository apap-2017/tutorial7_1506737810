package com.example.dao;

import java.util.List;

import com.example.model.CourseModel;

public interface CourseDAO {
	//Implementasi pada class Course
		CourseModel selectCourse(String idCourse);
		List<CourseModel> selectAllCourses();
}
