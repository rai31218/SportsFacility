//package com.sports.facility.controller;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.TimeZone;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sports.facility.dto.AvailabilityDTO;
//import com.sports.facility.model.BookingDetails;
//import com.sports.facility.model.Facilities;
//import com.sports.facility.model.Players;
//import com.sports.facility.model.TimeSlots;
//import com.sports.facility.response.MessageResponse;
//import com.sports.facility.service.BookingDetailsService;
//import com.sports.facility.service.PlayersService;
//
//@RestController
//@CrossOrigin(origins = { "http://localhost:4200", "https://digitalbook-frontend.s3.amazonaws.com" })
//@RequestMapping(value = "/sports")
//public class BookingController {
//
//	@Autowired
//	BookingDetailsService bookingDetailsService;
//
//	@Autowired
//	PlayersService playerService;
//
//	@PostMapping("/bookfacility")
//	public ResponseEntity<?> bookFacility(@RequestBody BookingDetails bookingDetails) {
//		try {
//			Optional<Players> player = playerService.findByPlayerId(bookingDetails.getPlayer().getId());
//
//			if (player.isPresent()) {
//				return bookingDetailsService.bookFacility(bookingDetails);
//
//			} else {
//				return ResponseEntity.badRequest().body(new MessageResponse("Not a valid Player Id"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@GetMapping("/getfacilities")
//	public ResponseEntity<?> getFacilities() {
//		List<Facilities> faciliyList = bookingDetailsService.getFacilities();
//		return ResponseEntity.ok(faciliyList);
//
//	}
//
//	@GetMapping("/availability")
//	public List<TimeSlots> availableSchedule(@RequestParam("facilityId") String facilityId,
//			@RequestParam("facilityName") String facilityName,
//			@RequestParam("date") String date) throws ParseException {
//		//Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z").parse(date.replace('"', ' ')); 
//		
//		
//		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//
//		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
//		Date date1 = inputFormat.parse(date.replace('"', ' '));
//		String date12 = outputFormat.format(date1);
//	
//		
//		SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
//		inputFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
//		Date date123 = inputFormat1.parse(date12.replace('"', ' '));
//
//		
//		AvailabilityDTO avilabilitycheck = new AvailabilityDTO(date123, new Facilities(Integer.parseInt(facilityId), facilityName));
//		List<TimeSlots> timeSlotList = bookingDetailsService
//				.checkAvailabilityForListingScehdule(((AvailabilityDTO) avilabilitycheck).getFacility(), ((AvailabilityDTO) avilabilitycheck).getDate());
//
//		return timeSlotList;
//
//	}
//	
//	@GetMapping("/getBookings/{player-id}")
//	public List<BookingDetails> getBookings(@PathVariable("player-id") String playerId){
//		return bookingDetailsService.getBooking(playerId);
//		 
//	}
//
//}
