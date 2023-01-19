package com.sports.facility.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.sports.facility.dto.BookingDetailsDTO;
import com.sports.facility.dto.FacilitiesDTO;
import com.sports.facility.dto.TimeSlotsDTO;
import com.sports.facility.model.Players;

import com.sports.facility.response.MessageResponse;

import com.sports.facility.service.PlayersService;

@CrossOrigin(origins = { "http://localhost:4200", "https://sports-facility-bucket1.s3.amazonaws.com" })
@RestController
@RequestMapping(value = "/sports")
public class BookingControllerTrial {


	@Autowired
	PlayersService playerService;
	
	@Autowired
	RestTemplate restTemplate;
	
	String bookingFacilityUrl = "http://localhost:8082/sports/";

	@PostMapping("/bookfacility")
	public ResponseEntity<?> bookFacility(@RequestBody BookingDetailsDTO bookingDetails) {
		try {
			Optional<Players> player = playerService.findByPlayerId(bookingDetails.getPlayer().getId());

			if (player.isPresent()) {
				ResponseEntity<MessageResponse> bookedFacility = restTemplate.postForEntity(bookingFacilityUrl+"bookfacility", bookingDetails, MessageResponse.class);		
				 
				//return bookingDetailsService.bookFacility(bookingDetails);
				return bookedFacility;

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Not a valid Player Id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/getfacilities")
	public ResponseEntity<?> getFacilities() {
		//List<Facilities> faciliyList = bookingDetailsService.getFacilities();
		FacilitiesDTO[] facilityList = restTemplate.getForObject(bookingFacilityUrl+"getfacilities",
				FacilitiesDTO[].class);	
		return ResponseEntity.ok(Arrays.asList(facilityList));

	}

	@GetMapping("/availability")
	public List<TimeSlotsDTO> availableSchedule(@RequestParam("facilityId") String facilityId,
			@RequestParam("facilityName") String facilityName,
			@RequestParam("date") String date) throws ParseException {
       
		TimeSlotsDTO[] timeSlotList = restTemplate.getForObject(bookingFacilityUrl+"availability/"+facilityId+"/"+facilityName+"/"+date,
				TimeSlotsDTO[].class);		
		return Arrays.asList(timeSlotList);

	}
	
	@GetMapping("/getBookings/{player-id}")
	public List<BookingDetailsDTO> getBookings(@PathVariable("player-id") String playerId){
		BookingDetailsDTO[] bookingList = restTemplate.getForObject(bookingFacilityUrl+"getBookings/"+playerId,
				BookingDetailsDTO[].class);		
		return Arrays.asList(bookingList);
		//return bookingDetailsService.getBooking(playerId);
		 
	}

}
