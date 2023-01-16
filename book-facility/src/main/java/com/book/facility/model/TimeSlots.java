package com.book.facility.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeslot")
public class TimeSlots {
	
	@Id
	private int id;
	private String timing;
	public TimeSlots() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TimeSlots(int id, String slot) {
		super();
		this.id = id;
		this.timing = slot;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, timing);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlots other = (TimeSlots) obj;
		return id == other.id && Objects.equals(timing, other.timing);
	}
	
	
}
