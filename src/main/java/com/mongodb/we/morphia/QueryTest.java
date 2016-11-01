package com.mongodb.we.morphia;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.we.morphia.dto.Department;
import com.mongodb.we.morphia.dto.Employee;
import com.mongodb.we.morphia.dto.Organization;

public class QueryTest {




	public static void main(String[] args) {

		MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
		MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

		Morphia morphia = new Morphia();
		Datastore ds = morphia.createDatastore(mongoClient, "test");

		Query<Employee> empQuery =  ds.createQuery(Employee.class);

		//		Employee employee = empQuery.get();

		//		this query get the first element in the collection!

		//		System.out.println(employee);


		//		List<Employee> list = empQuery.asList();

		//		System.out.println(list);

		//		This query gets the entire collection , needs to be carefull 
		//		with this in case if the size of the collection is huge


		/*Iterable<Employee> 	fetchEmployees = empQuery.fetch();

		Iterator<Employee> empItarator = fetchEmployees.iterator();

		System.out.println("****"+ empItarator.hasNext());

		while (empItarator.hasNext()) {
			Employee employee = (Employee) empItarator.next();

			System.out.println(employee);

		}
		System.out.println("RE fetch");


		empItarator = fetchEmployees.iterator();
		System.out.println("****"+ empItarator.hasNext());
		while (empItarator.hasNext()) {
			Employee employee = (Employee) empItarator.next();

			System.out.println("****"+employee);

		}*/

		//fetch is safer than the list in case the collection is large


		
		List<Employee> empList =  empQuery.field("salary").greaterThan(100).asList();

		System.out.println(empList);

		//		ds.save(employee);
		
		
		
		System.out.println("Employee Saved");






	}

}
