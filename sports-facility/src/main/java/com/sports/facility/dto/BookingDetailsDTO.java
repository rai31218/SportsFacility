package com.sports.facility.dto;

import java.util.Date;


import com.sports.facility.model.Players;


public class BookingDetailsDTO {

    private String id;
	

	private Players player;
	
	private Date bookingDate;

	private TimeSlotsDTO bookingSlot;

	private FacilitiesDTO facility;
	
	
	
	
	public BookingDetailsDTO() {
		super();

	}

	public BookingDetailsDTO(String id, Players player, Date bookingDate, TimeSlotsDTO bookingSlot, FacilitiesDTO facilityName) {
		super();
		this.id = id;
		this.player = player;
		this.bookingDate = bookingDate;
		this.bookingSlot = bookingSlot;
		this.facility = facilityName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Players getPlayer() {
		return player;
	}
	public void setPlayer(Players player) {
		this.player = player;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public TimeSlotsDTO getBookingSlot() {
		return bookingSlot;
	}
	public void setBookingSlot(TimeSlotsDTO bookingSlot) {
		this.bookingSlot = bookingSlot;
	}

	public FacilitiesDTO getFacility() {
		return facility;
	}

	public void setFacility(FacilitiesDTO facilityName) {
		this.facility = facilityName;
	}

	@Override
	public String toString() {
		return "BookingDetails [id=" + id + ", player=" + player + ", bookingDate=" + bookingDate + ", bookingSlotId="
				+ bookingSlot+ ", facility=" + facility + "]";
	}

	
	
}
