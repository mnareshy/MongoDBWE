package com.mongodb.we.morphia.dto;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("managers")
@Indexes(
    @Index(value = "salary", fields = @Field("salary"))
)
public class Manager {
	public Manager(String name, Double salary) {
		
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
    private List<Manager> directReports = new ArrayList();
    private Double salary;
	public List<Manager> getDirectReports() {
		return directReports;
	}
	
	
	public void setDirectReports(List<Manager> directReports) {
		this.directReports = directReports;
	}


	public Manager getManager() {
		return manager;
	}


	public void setManager(Manager manager) {
		this.manager = manager;
	}
}