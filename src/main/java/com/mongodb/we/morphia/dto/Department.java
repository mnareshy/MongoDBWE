package com.mongodb.we.morphia.dto;


import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = "department" , noClassnameStored = true)

public class Department {
	
	public Department(String name )
	{
		this.name = name;
	}
	
	@Id
	private String name;
	private int id;
	private String desc;
	private int noOfEmp;
	@Indexed(value = IndexDirection.ASC, name = "" , unique = false, dropDups = false, expireAfterSeconds = -1, 
			background = false , sparse = false)
	private Date dateOfEstablishment;
	//  indexes can be mentioned at the field level.
	//  value is to set the direction , if no name mentioned it infers from field
	//	dropDups is useful when unique is used
	//	It says, when  you create index, it it happens to find a non-unique value in the collection as it indexing it,
	//	just drop that particular document from collection!
	//  if the background is false , indexes will create in the foreground, if the collection is big 
	//	it might hold the screen for long time
	//  sparse is used to specify that the created filed is not in all documents

	@Version("v")
	private long version;
	// when ever Marphia saves the Department , increment this field value.
	// if the old values was modified by the user, it error outs that it has to handled manually!

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getNoOfEmp() {
		return noOfEmp;
	}

	public void setNoOfEmp(int noOfEmp) {
		this.noOfEmp = noOfEmp;
	}

	public Date getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(Date dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
