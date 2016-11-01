package com.mongodb.we.morphia.dto;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;


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
	@Property("department")
	@Reference(lazy=true)
	private Department dept;
	//if the you want the collection field name is different from the java field mention using @Property
	//@Reference lazy property is to say don't load dept when i access Employee, load it when i invoke dept
	//@Reference can also be used to set a property in the collection rather loading it completely
	private Date dateOfJoining;
	private boolean isActive;
	private Date dateOfResign;
	private int salary;


}
