package com.book.facility.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.facility.model.BookingDetails;
import com.book.facility.model.Facilities;
import com.book.facility.model.TimeSlots;

@Service
public interface BookingDetailsService {
	ResponseEntity<?> bookFacility(BookingDetails bookingDetails );

	List<TimeSlots> checkAvailability(Facilities facility, Date bookingDate, TimeSlots timeSlot);

	List<Facilities> getFacilities();

	List<TimeSlots> checkAvailabilityForListingScehdule(Facilities facility, Date bookingDate);

	List<BookingDetails> getBooking(String playerId);
}
