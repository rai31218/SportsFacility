package com.sports.facility.dto;

import java.util.Date;
import java.util.Objects;


public class AvailabilityDTO {
	private Date date;
	private FacilitiesDTO facility;
	public AvailabilityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvailabilityDTO(Date date, FacilitiesDTO facility) {
		super();
		this.date = date;
		this.facility = facility;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public FacilitiesDTO getFacility() {
		return facility;
	}
	public void setFacility(FacilitiesDTO facility) {
		this.facility = facility;
	}
	@Override
	public int hashCode() {
		return Objects.hash(date, facility);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvailabilityDTO other = (AvailabilityDTO) obj;
		return Objects.equals(date, other.date) && Objects.equals(facility, other.facility);
	}
	
	

}
