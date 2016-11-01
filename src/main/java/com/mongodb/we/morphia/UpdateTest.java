package com.mongodb.we.morphia;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.we.morphia.dto.Department;
import com.mongodb.we.morphia.dto.Employee;
import com.mongodb.we.morphia.dto.Organization;

public class UpdateTest {




	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		Morphia morphia = new Morphia();
		Datastore ds = morphia.createDatastore(mongoClient, "test");

		/*	Query<Employee> empQuery =  ds.createQuery(Employee.class);

		Employee employee = empQuery.get();
		employee.setSalary(1000000000);


		ds.save(employee);*/




		UpdateOperations<Employee> massUpdate = ds.createUpdateOperations(Employee.class)
				.inc("salary", 100)
				.set("isActive", false);

		Query<Employee> empQuery = ds.createQuery(Employee.class).field("empID").lessThan(100);

		ds.update(empQuery,massUpdate);


		//		This is very usefull when you do mass updates!


		System.out.println("Employee Saved");






	}

}
