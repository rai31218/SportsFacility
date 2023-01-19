package com.book.facility.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.facility.model.BookingDetails;
import com.book.facility.model.Facilities;
import com.book.facility.model.TimeSlots;
import com.book.facility.response.MessageResponse;
import com.book.facility.dto.AvailabilityDTO;
import com.book.facility.service.BookingDetailsService;


@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://digitalbook-frontend.s3.amazonaws.com" })
@RequestMapping(value = "/sports")
public class BookingController {

	@Autowired
	BookingDetailsService bookingDetailsService;

	@PostMapping("/bookfacility")
	public ResponseEntity<?> bookFacility(@RequestBody BookingDetails bookingDetails) {
		try {
			ResponseEntity<?> response = bookingDetailsService.bookFacility(bookingDetails);
			return response;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Something went wrong!"));
		}
		
	}

	@GetMapping("/getfacilities")
	public ResponseEntity<?> getFacilities() {
		List<Facilities> faciliyList = bookingDetailsService.getFacilities();
		return ResponseEntity.ok(faciliyList);

	}

	@GetMapping("/availability/{facilityId}/{facilityName}/{date}")
	public List<TimeSlots> availableSchedule(@PathVariable("facilityId") String facilityId,
			@PathVariable("facilityName") String facilityName,
			@PathVariable("date") String date) throws ParseException {
		try {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
		Date date1 = inputFormat.parse(date.replace('"', ' '));
		String date12 = outputFormat.format(date1);
	
		
		SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
		inputFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date123 = inputFormat1.parse(date12.replace('"', ' '));

		
		AvailabilityDTO avilabilitycheck = new AvailabilityDTO(date123, new Facilities(Integer.parseInt(facilityId), facilityName));
		List<TimeSlots> timeSlotList = new ArrayList<TimeSlots>();
		timeSlotList = bookingDetailsService
				.checkAvailabilityForListingScehdule(avilabilitycheck.getFacility(),  avilabilitycheck.getDate());

		return timeSlotList;
		}
		catch (Exception e) {
			return null;
		}

	}
	
	@GetMapping("/getBookings/{player-id}")
	public List<BookingDetails> getBookings(@PathVariable("player-id") String playerId){
		return bookingDetailsService.getBooking(playerId);
		 
	}

}
