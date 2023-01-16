package com.sports.facility.dto;

import java.util.ArrayList;
import java.util.List;


public class TimeSlotList {
	private List<TimeSlotsDTO> timeslotList;
	
	 public TimeSlotList() {
		 timeslotList = new ArrayList<>();
	    }

	public TimeSlotList(List<TimeSlotsDTO> timeslotList) {
		super();
		this.timeslotList = timeslotList;
	}

	public List<TimeSlotsDTO> getTimeslotList() {
		return timeslotList;
	}

	public void setTimeslotList(List<TimeSlotsDTO> timeslotList) {
		this.timeslotList = timeslotList;
	}
	 
	 

}
