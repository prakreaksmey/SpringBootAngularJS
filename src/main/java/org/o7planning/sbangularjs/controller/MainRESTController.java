package org.o7planning.sbangularjs.controller;

import java.util.List;


import org.o7planning.sbangularjs.model.Student;
import org.o7planning.sbangularjs.servies.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
  
@RestController 
public class MainRESTController {
  
    @Autowired
    private StudentService StudentService;
  
  
    // URL:
    // http://localhost:8080/SomeContextPath/students
    // http://localhost:8080/SomeContextPath/students.xml
    // http://localhost:8080/SomeContextPath/students.json
    @RequestMapping(value = "/students", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Student> getStudents() {
        List<Student>students= StudentService.lstStudent();
        
        return students;
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/student/{stuId}
    // http://localhost:8080/SomeContextPath/student/{stuId}.xml
    // http://localhost:8080/SomeContextPath/student/{stuId}.json
    @RequestMapping(value = "/student/{stuId}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Student getStudent(@PathVariable("stuId") Long stuId) {
        return StudentService.getById(stuId);
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
  
    @RequestMapping(value = "/student", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Student addStudent(@RequestBody Student student) {
  
        System.out.println("(Service Side) Creating student with stuName: " + student.getStuName());
  
         StudentService.addStudent(student);
		return student;
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
    @RequestMapping(value = "/student/{id}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void  updateStudent(@RequestBody Student student,@PathVariable Long id) {
  
       
  
         StudentService.upDateStudent(student, id);
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empId}
    @RequestMapping(value = "/student/{stuId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteStudent(@PathVariable("stuId") Long stuId) {
  
        System.out.println("(Service Side) Deleting student with Id: " + stuId);
  
        StudentService.deletStudent(stuId);
    }
  
}