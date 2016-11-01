package com.mongodb.we.morphia.dto;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;


@Entity(value = "organization" , noClassnameStored = true)
public class Organization {
	
	public Organization( String name)
	{
		
		this.name = name;
	}
	@Id
	private String name;
	
	@Reference
	private Department department;
	
	@Reference
	private Employee employee;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}
