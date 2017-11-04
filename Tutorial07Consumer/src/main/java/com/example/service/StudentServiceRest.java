package com.example.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.dao.CourseDAO;
import com.example.dao.StudentDAO;
import com.example.model.CourseModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class StudentServiceRest implements StudentService {
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private CourseDAO courseDAO;

	@Override
	public StudentModel selectStudent(String npm) {
		log.info ("REST - select student with npm {}", npm);
		return studentDAO.selectStudent(npm);
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		log.info("REST - select all students");
		return studentDAO.selectAllStudents();
	}

	@Override
	public CourseModel selectCourse(String idCourse) {
		log.info ("REST - select course with id {}", idCourse);		
		return courseDAO.selectCourse(idCourse);
	}
	
	@Override
	public List<CourseModel> selectAllCourses() {
		log.info("REST - select all courses");
		return courseDAO.selectAllCourses();
	}

	@Override
	public void addStudent(StudentModel student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(String npm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStudent(StudentModel student) {
		// TODO Auto-generated method stub
		
	}
}
