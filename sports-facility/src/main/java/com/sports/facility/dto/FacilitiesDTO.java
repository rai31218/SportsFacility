package com.sports.facility.dto;

public class FacilitiesDTO {

	private int id;
	private String name;
	private String facility;
	private int initialAvailability;

	public FacilitiesDTO() {
		super();

	}

	public FacilitiesDTO(int id, String name, String facility, int initialAvailability) {
		super();
		this.id = id;
		this.name = name;
		this.facility = facility;
		this.initialAvailability = initialAvailability;
	}

	public FacilitiesDTO(int id, String name) {
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
