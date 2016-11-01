package com.mongodb.we.morphia.dto;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Version;


@Entity(value = "employee" , noClassnameStored = true)
//not mandatory , but if you want the name of the collection different from the java class name use this!
//Marphia stored the class name as metadata for the collection , noClassnameStored set to say dont store
@Indexes({

	@Index(value = "empID,-dateOfJoining", name="prime"),
	//this is to create ascending index on empID ,descending  index on dateOfJoining
	@Index(value = "dateOfResign", name = "clearresigned" , expireAfterSeconds=1000000)
	//this will delete the document after the expiry seconds mentioned, this is 
	//	usefull delete clear the records which are no longer valid
})

public class Employee {

	private String empName;
	@Id
	private int empID;
	//to mention the id field
	//	@Property("department")
	@Reference(lazy=true)
	private Department department;
	//if the you want the collection field name is different from the java field mention using @Property
	//@Reference lazy property is to say don't load department when i access Employee, load it when i invoke department
	//@Reference can also be used to set a property in the collection rather loading it completely



	private Date dateOfJoining;
	private boolean isActive;
	private Date dateOfResign;
	private int salary;


	@Version
	private int version;


	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getDateOfResign() {
		return dateOfResign;
	}
	public void setDateOfResign(Date dateOfResign) {
		this.dateOfResign = dateOfResign;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String toString()
	{
		return "Name :" +empName+" , empId : "+empID;
	}



}
