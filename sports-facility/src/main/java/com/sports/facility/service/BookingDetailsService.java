//package com.sports.facility.service;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//
//import com.sports.facility.model.BookingDetails;
//import com.sports.facility.model.Facilities;
//import com.sports.facility.model.TimeSlots;
//import com.sports.facility.response.MessageResponse;
//
//public interface BookingDetailsService {
//	ResponseEntity<MessageResponse> bookFacility(BookingDetails bookingDetails );
//
//	List<TimeSlots> checkAvailability(Facilities facility, Date bookingDate, TimeSlots timeSlot);
//
//	List<Facilities> getFacilities();
//
//	List<TimeSlots> checkAvailabilityForListingScehdule(Facilities facility, Date bookingDate);
//
//	List<BookingDetails> getBooking(String playerId);
//}
