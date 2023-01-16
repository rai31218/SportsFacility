package com.book.facility.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Availability {
	
	@Id
	 private int id;
	
	@DocumentReference(lazy=true)
	 private Facilities facilityId;
	
	@DocumentReference(lazy=true)
	 private TimeSlots timeSlotId;
	
	 private int currentAvailability;

	public Availability(int id, Facilities facilityId, TimeSlots timeSlotId, int currentAvailability) {
		super();
		this.id = id;
		this.facilityId = facilityId;
		this.timeSlotId = timeSlotId;
		this.currentAvailability = currentAvailability;
	}

	public Availability() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Facilities getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Facilities facilityId) {
		this.facilityId = facilityId;
	}

	public TimeSlots getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(TimeSlots timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public int getCurrentAvailability() {
		return currentAvailability;
	}

	public void setCurrentAvailability(int currentAvailability) {
		this.currentAvailability = currentAvailability;
	}
	 
	 
	 
	 

}
