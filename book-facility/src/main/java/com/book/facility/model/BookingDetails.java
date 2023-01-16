package com.book.facility.model;

import java.util.Date;
import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.book.facility.dto.PlayersDTO;



@Document(collection = "bookingDetails")
public class BookingDetails {
	@Transient
	public static final String SEQUENCE_NAME = "booking_details_sequence";
	@Id
    private String id;
	
	@DocumentReference(lazy=true)
	private PlayersDTO player;
	
	private Date bookingDate;
	
	@DocumentReference(lazy=false)
	private TimeSlots bookingSlot;
	
	@DocumentReference(lazy=true)
	private Facilities facility;
	
	
	
	
	public BookingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingDetails(String id, PlayersDTO player, Date bookingDate, TimeSlots bookingSlot, Facilities facilityName) {
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
	public PlayersDTO getPlayer() {
		return player;
	}
	public void setPlayer(PlayersDTO player) {
		this.player = player;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public TimeSlots getBookingSlot() {
		return bookingSlot;
	}
	public void setBookingSlot(TimeSlots bookingSlot) {
		this.bookingSlot = bookingSlot;
	}

	public Facilities getFacility() {
		return facility;
	}

	public void setFacility(Facilities facilityName) {
		this.facility = facilityName;
	}

	@Override
	public String toString() {
		return "BookingDetails [id=" + id + ", player=" + player + ", bookingDate=" + bookingDate + ", bookingSlotId="
				+ bookingSlot+ ", facility=" + facility + "]";
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(bookingDate, bookingSlot, facility, id, player);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BookingDetails other = (BookingDetails) obj;
//		return Objects.equals(bookingDate, other.bookingDate) && Objects.equals(bookingSlot, other.bookingSlot)
//				&& Objects.equals(facility, other.facility) && Objects.equals(id, other.id)
//				&& Objects.equals(player, other.player);
//	}
//	
	
	
}
