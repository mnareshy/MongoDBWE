package com.mongodb.we.morphia;

import java.sql.Date;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.we.morphia.dto.Department;
import com.mongodb.we.morphia.dto.Employee;
import com.mongodb.we.morphia.dto.Organization;

public class InsertTest {




	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		Morphia morphia = new Morphia();
		Datastore ds = morphia.createDatastore(mongoClient, "test");

		Employee employee = new Employee();

		employee.setEmpID(3200);
		employee.setEmpName("Emp4");
		employee.setActive(false);
		employee.setDateOfJoining(new Date(2012,12,03));
		employee.setSalary(220000);
		employee.setDateOfResign(new Date(2016,12,03));

		
		ds.save(employee);
		System.out.println("Employee Saved");

		




	}

}
