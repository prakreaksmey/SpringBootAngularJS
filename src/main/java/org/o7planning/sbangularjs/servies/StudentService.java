package org.o7planning.sbangularjs.servies;

import java.util.List;

import org.o7planning.sbangularjs.model.Student;


public interface StudentService {
	 List<Student>lstStudent();
	Student getById(Long id);
	void  addStudent(Student student);
	void upDateStudent(Student student,Long id);
	void deletStudent(Long id);
	 

}
