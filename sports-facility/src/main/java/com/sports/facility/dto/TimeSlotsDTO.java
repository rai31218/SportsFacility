package com.sports.facility.dto;

import java.util.Objects;


public class TimeSlotsDTO {
	

	private int id;
	private String timing;
	public TimeSlotsDTO() {
		super();

	}
	public TimeSlotsDTO(int id, String slot) {
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
		TimeSlotsDTO other = (TimeSlotsDTO) obj;
		return id == other.id && Objects.equals(timing, other.timing);
	}
	
	
}
