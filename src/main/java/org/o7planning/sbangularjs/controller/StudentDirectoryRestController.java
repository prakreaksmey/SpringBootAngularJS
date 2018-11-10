package org.o7planning.sbangularjs.controller;

import org.o7planning.sbangularjs.exception.MyResourceNotFoundException;
import org.o7planning.sbangularjs.model.Student;
import org.o7planning.sbangularjs.servies.StudentServicePagin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class StudentDirectoryRestController {
	@Autowired
	 private StudentServicePagin studentServicePagin;
	   @RequestMapping(value = "/student/get", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	    public Page<Student> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {

		 Page<Student> resultPage = studentServicePagin.findPaginated(page, size);
	        if (page > resultPage.getTotalPages()) {
	            throw new MyResourceNotFoundException();
	        }
	        return resultPage;
	    }
	
	
	

}
