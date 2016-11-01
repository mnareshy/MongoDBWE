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

public class VersionTest {




	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		Morphia morphia = new Morphia();
		Datastore ds = morphia.createDatastore(mongoClient, "test");

		Query<Employee> empQuery =  ds.createQuery(Employee.class);

		Employee employee = empQuery.get();
		Employee employee1 = empQuery.get();

		System.out.println(employee +":"+employee1);

		ds.save(employee);
		ds.save(employee);
		ds.save(employee1);





		System.out.println("Employee Saved");






	}

}
