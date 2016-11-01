package com.mongodb.we.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.we.morphia.dto.Employee2;
import com.mongodb.we.morphia.dto.Manager;


public class TeatMarphia2 {
	
	MongoClientOptions clientOptions = MongoClientOptions.builder().connectionsPerHost(12).build();
	MongoClient mongoClient =  new  MongoClient(new ServerAddress("localhost",27017),clientOptions);

	Morphia morphia = new Morphia();
	Datastore datastore = morphia.createDatastore(mongoClient, "test");
	
	public void saveOrdDeptEmp()
	{
		
	

		final Employee2 Nitta = new Employee2("Nitta Gangadhar", 50000.0);
		datastore.save(Nitta);
		
		
		final Employee2 Rama = new Employee2("Rama", 40000.0);
		datastore.save(Rama);

		final Employee2 saran = new Employee2("saran", 25000.0);
		datastore.save(saran);

		Nitta.getDirectReports().add(Rama);
		Nitta.getDirectReports().add(saran);
		
		
		final Manager sachin = new Manager("sachin", 50000.0);
		datastore.save(sachin);
		
		Nitta.setManager(sachin);

		datastore.save(Nitta);
		
	}
		
	

	public static void main(String[] args) {
		

		new TeatMarphia2().saveOrdDeptEmp();
		
		

	}
	
	
	
	
	

}
