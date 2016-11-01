package com.mongodb.we.morphia.dto;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

@Entity("employees2")
@Indexes(
    @Index(value = "salary", fields = @Field("salary"))
)
public class Employee2 {
	public Employee2(String name, Double salary) {
		
		this.name = name;
		this.salary = salary;
		// TODO Auto-generated constructor stub
	}

    @Id
    private ObjectId id;
    private String name;
    @Reference
    private Manager manager;
    @Reference
    private List<Employee2> directReports = new ArrayList();
    private Double salary;
	public List<Employee2> getDirectReports() {
		return directReports;
	}
	
	
	public void setDirectReports(List<Employee2> directReports) {
		this.directReports = directReports;
	}


	public Manager getManager() {
		return manager;
	}


	public void setManager(Manager manager) {
		this.manager = manager;
	}
}