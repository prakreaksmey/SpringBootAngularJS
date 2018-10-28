package org.o7planning.sbangularjs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue
	 private Long stuId;
	    private String stuName;
	    private String stuSex;
	    private String Address;
	    
	    
	    public Student(StudentForm stuForm)
	    {
	    	this.stuId=stuForm.getStuId();
	    	this.stuName=stuForm.getStuName();
	    	this.stuSex=stuForm.getStuSex();
	    	this.Address=stuForm.getAddress();
	    }
	    
	    public Student() 
	    {
	    	
	    }
	    
	    
		public Student(Long stuId, String stuName, String stuSex, String address) {
		
			this.stuId = stuId;
			this.stuName = stuName;
			this.stuSex = stuSex;
			Address = address;
		}

		public Long getStuId() {
			return stuId;
		}

		public void setStuId(Long stuId) {
			this.stuId = stuId;
		}

		public String getStuName() {
			return stuName;
		}

		public void setStuName(String stuName) {
			this.stuName = stuName;
		}

		public String getStuSex() {
			return stuSex;
		}

		public void setStuSex(String stuSex) {
			this.stuSex = stuSex;
		}

		public String getAddress() {
			return Address;
		}

		public void setAddress(String address) {
			Address = address;
		}
	    
	    
}
