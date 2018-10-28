package org.o7planning.sbangularjs.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
  
import org.o7planning.sbangularjs.model.Student;
import org.o7planning.sbangularjs.model.StudentForm;
import org.springframework.stereotype.Repository;
  
@Repository
public class StudentDAO {
	
	public static final Map<Long,Student>stuMap=new  HashMap<Long,Student>();
//	
//	static {
//		initStus();
//	}
	
	
//	public static void initStus()
//	{
//		Student stu1=new Student(1l,"dara","Male","PP");
//		Student stu2=new Student(2l,"vireak","Male","SR");
//		Student stu3=new Student(3l,"thida","Female","PR");
//		
//		
//		stuMap.put(stu1.getStuId(), stu1);
//		stuMap.put(stu2.getStuId(), stu2);
//		stuMap.put(stu3.getStuId(), stu3);
//	}
//	
	
	 public Long getMaxStuId() {
	        Set<Long> keys = stuMap.keySet();
	        Long max = 0L;
	        for (Long key : keys) {
	            if (key > max) {
	                max = key;
	            }
	        }
	        return max;
	    }
	
	 public Student getStudent(Long stuId) {
	        return stuMap.get(stuId);
	    }
	
	
	 public Student addStudent(StudentForm stuForm) {
	        Long stuId= this.getMaxStuId()+ 1;
	        stuForm.setStuId(stuId);
	        Student newStu = new Student(stuForm);  
	         
	        stuMap.put(newStu.getStuId(), newStu);
	        return newStu;
	    }
	  
	   public Student updateStudent(StudentForm stuForm) {
		   Student stu = this.getStudent(stuForm.getStuId());
	        if(stu!= null)  {
	        	stu.setStuName(stuForm.getStuName());
	        	stu.setStuSex(stuForm.getStuSex());
	        	stu.setAddress(stuForm.getAddress());
	        }  
	        return stu;
	    }
	
	   public void deleteStudent(Long stuId) {
	        stuMap.remove(stuId);
	    }
	   
	   
	   
	   public List<Student> getAllStudents() {
	        Collection<Student> c = stuMap.values();
	        List<Student> list = new ArrayList<Student>();
	        list.addAll(c);
	        return list;
	    }
}
	   
	   