package com.book.facility.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.facility.dto.PlayersDTO;
import com.book.facility.model.BookingDetails;
import com.book.facility.model.DatabaseSequence;
import com.book.facility.model.Facilities;
import com.book.facility.model.TimeSlots;
import com.book.facility.repository.BookingDetailsRepository;
import com.book.facility.repository.FacilityRepository;
import com.book.facility.response.MessageResponse;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

	@Autowired
	BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	FacilityRepository facilityRepository;

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	TimeSlotsRepository timeSlotRepository;

	@Override
	public ResponseEntity<MessageResponse> bookFacility(BookingDetails bookingDetails) {
		bookingDetails.setId(UUID.randomUUID().toString());
		List<TimeSlots> timeSlots = checkAvailability(bookingDetails.getFacility(), bookingDetails.getBookingDate(),
				bookingDetails.getBookingSlot());
		if (!timeSlots.contains(bookingDetails.getBookingSlot())) {
			System.out.println("Lets see the date: " + bookingDetails.getBookingDate());
			return ResponseEntity.status(HttpStatus.OK)
					// badRequest()
					.body(new MessageResponse("Facility not available for this slot"));
		}

		else {
			String bookingId = bookingDetailsRepository.save(bookingDetails).getId();
			return ResponseEntity.ok()
					.body(new MessageResponse("Facility has been booked for you with booking Id: " + bookingId));
		}

	}

	@Override
	public List<TimeSlots> checkAvailability(Facilities facility, Date bookingDate, TimeSlots timeSlot) {

		List<BookingDetails> bookingDetais = bookingDetailsRepository
				.findByFacilityAndBookingDateAndBookingSlot(facility, bookingDate, timeSlot);
		List<TimeSlots> timeslots = timeSlotRepository.findAll();
		List<String> checkSameList = new ArrayList<String>();
		for (BookingDetails bookingDetail : bookingDetais) {
			String checkSame = bookingDetail.getFacility().getName() + " " + bookingDetail.getBookingDate() + " "
					+ bookingDetail.getBookingSlot();
			checkSameList.add(checkSame);
		}

		if (facility.getId() == 1 && checkSameList.size() == 2) {
			timeslots.remove(bookingDetais.get(0).getBookingSlot());

		}
		if (facility.getId() == 2 && checkSameList.size() == 1) {
			timeslots.remove(bookingDetais.get(0).getBookingSlot());
		}

		if (facility.getId() == 3 && checkSameList.size() == 3) {
			timeslots.remove(bookingDetais.get(0).getBookingSlot());
		}
		return timeslots;
	}

	@Override
	public List<TimeSlots> checkAvailabilityForListingScehdule(Facilities facility, Date bookingDate) {

		List<BookingDetails> bookingDetais = bookingDetailsRepository.findByFacilityAndBookingDate(facility,
				bookingDate);
		List<TimeSlots> timeslots = timeSlotRepository.findAll();
		List<TimeSlots> checkSameList = new ArrayList<TimeSlots>();

		for (BookingDetails bookingDetail : bookingDetais) {
			TimeSlots checkSame = bookingDetail.getBookingSlot();
			checkSameList.add(checkSame);

		}

		if (facility.getId() == 1 && checkSameList.size() >= 2) {

			for (TimeSlots ts : timeslots) {
				if (Collections.frequency(checkSameList, ts) >= 2) {
					timeslots.remove(ts);
					break;
				}
			}

		}
		if (facility.getId() == 2 && checkSameList.size() >= 1) {

			for (TimeSlots ts : timeslots) {
				if (Collections.frequency(checkSameList, ts) >= 1) {
					timeslots.remove(ts);
					break;
				}
			}
		}

		if (facility.getId() == 3 && checkSameList.size() >= 3) {

			for (TimeSlots ts : timeslots) {
				if (Collections.frequency(checkSameList, ts) >= 3) {
					timeslots.remove(ts);
					break;
				}
			}
		}
		return timeslots;
	}

	public int generateSequence(String seqName) {

		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return (int) (!Objects.isNull(counter) ? counter.getSeq() : 1);

	}

	@Override
	public List<Facilities> getFacilities() {

		return facilityRepository.findAll();
	}

	@Override
	public List<BookingDetails> getBooking(String playerId) {
		PlayersDTO player = new PlayersDTO();
		player.setId(playerId);
		List<BookingDetails> bookingList = bookingDetailsRepository.findByPlayer(player);

		return bookingList;

	}

}
