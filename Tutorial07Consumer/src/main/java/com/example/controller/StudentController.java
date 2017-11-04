package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("title","Home");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("title","Add Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa,null);
        studentDAO.addStudent (student);
    	model.addAttribute("title","Sukses!");
        return "success-add";
    }

    
    @RequestMapping("/student/update/submit")
    public String updateSubmit (Model model,
    		@Valid @ModelAttribute StudentModel student)
    {
    	studentDAO.updateStudent(student);
    	model.addAttribute("title","Sukses!");
    	return "success-update";
    }
    
    
    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title","View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title","Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);
        
        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title","View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title","View Student");
            return "not-found";
        }
    }
    
    @RequestMapping("/course/view/{id_course}")
    public String viewCourse (Model model,
            @PathVariable(value = "id_course") String id_course)
    {
        CourseModel course = studentDAO.selectCourse(id_course);
        
        if (course != null) {
            model.addAttribute ("course", course);
            model.addAttribute("title","View Course");
            return "view-course";
        } else {
            model.addAttribute ("id_course", id_course);
            model.addAttribute("title","Not Found!");
            return "not-found";
        }
    }
    
    @RequestMapping("/course/viewall")
    public String viewAllCourse (Model model)
    {
        List<CourseModel> courses = studentDAO.selectAllCourses ();
        model.addAttribute ("courses", courses);
        model.addAttribute("title","View All Courses");
        return "course-viewall";
    }

    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title","View All Students");
        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, 
    		@PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            studentDAO.deleteStudent (npm);
            model.addAttribute("title","Delete Student");
            return "delete";
        } else {
        	 model.addAttribute("title","Not Found!");
            return "not-found";
        }
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, 
    		@PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            studentDAO.updateStudent(student);
            model.addAttribute("title","Update Student");
            return "form-update";
        } else {
        	 model.addAttribute("title","Not Found!");
            return "not-found";
        }
    }

}
