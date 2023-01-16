package com.book.facility.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.book.facility.dto.PlayersDTO;
import com.book.facility.model.BookingDetails;
import com.book.facility.model.Facilities;
import com.book.facility.model.TimeSlots;

public interface BookingDetailsRepository extends MongoRepository<BookingDetails, Integer> {

	List<BookingDetails> findByFacilityAndBookingDateAndBookingSlot(Facilities facility, Date bookingDate, TimeSlots bookingSlotId);

	List<BookingDetails> findByFacilityAndBookingDate(Facilities facility, Date bookingDate);

	List<BookingDetails> findByPlayer(PlayersDTO player);



}
