package org.o7planning.sbangularjs.servies;

import org.o7planning.sbangularjs.model.Student;
import org.o7planning.sbangularjs.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentServicePaginImpl implements StudentServicePagin{

	@Autowired
	
	private StudentRepository studentRepository;
	
	
	 @Override
	public Page<Student> findPaginated(int page, int size) {
		// TODO Auto-generated method stub
		 return studentRepository.findAll(new PageRequest(page, size));
	}
	

}
