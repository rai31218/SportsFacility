package com.book.facility.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facility")
public class Facilities {
	@Id
	private int id;
	private String name;
	private String facility;
	private int initialAvailability;
	

	public Facilities() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Facilities(int id, String name, String facility, int initialAvailability) {
		super();
		this.id = id;
		this.name = name;
		this.facility=facility;
		this.initialAvailability = initialAvailability;
	}
	public Facilities(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInitialAvailability() {
		return initialAvailability;
	}
	public void setInitialAvailability(int initialAvailability) {
		this.initialAvailability = initialAvailability;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	
	

}
