//package com.sports.facility.repository;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import com.sports.facility.model.BookingDetails;
//import com.sports.facility.model.Facilities;
//import com.sports.facility.model.Players;
//import com.sports.facility.model.TimeSlots;
//
//public interface BookingDetailsRepository extends MongoRepository<BookingDetails, Integer> {
//
//	List<BookingDetails> findByFacilityAndBookingDateAndBookingSlot(Facilities facility, Date bookingDate, TimeSlots bookingSlotId);
//
//	List<BookingDetails> findByFacilityAndBookingDate(Facilities facility, Date bookingDate);
//
//	List<BookingDetails> findByPlayer(Players player);
//
//
//
//}
