package com.mongodb.we.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import com.mongodb.we.morphia.dto.Department;
import com.mongodb.we.morphia.dto.Employee;
import com.mongodb.we.morphia.dto.Organization;

public class TeatMarphia {
	
	MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
	MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

	Morphia morphia = new Morphia();
	Datastore ds = morphia.createDatastore(mongoClient, "test");
	
	public void saveOrdDeptEmp()
	{
		Employee employee = new Employee();
		
		employee.setEmpID(38);
		employee.setEmpName("sairam");
		
		ds.save(employee);
		System.out.println("Employee Saved");
		
		Department department =  new Department("Product Development");
		department.setDesc("Does the product development");
		
		ds.save(department);
		
		employee.setDepartment(department);
		
		ds.save(employee);
		
		Organization organization =  new Organization( "StartUp1");
		organization.setDepartment(department);
		
		organization.setEmployee(employee);
		
//		System.out.println(organization.getDept().getDesc());
		ds.save(organization);
		
	}
		
	

	public static void main(String[] args) {
		

		new TeatMarphia().saveOrdDeptEmp();
		
		

	}
	
	
	
	
	

}
