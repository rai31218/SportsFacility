//package com.sports.facility.service;
//
//import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
//import static org.springframework.data.mongodb.core.query.Criteria.where;
//import static org.springframework.data.mongodb.core.query.Query.query;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.TimeZone;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.sports.facility.model.BookingDetails;
//import com.sports.facility.model.DatabaseSequence;
//import com.sports.facility.model.Facilities;
//import com.sports.facility.model.Players;
//import com.sports.facility.model.TimeSlots;
//import com.sports.facility.repository.BookingDetailsRepository;
//import com.sports.facility.repository.FacilityRepository;
//import com.sports.facility.response.MessageResponse;
//
//@Service
//public class BookingDetailsServiceImpl implements BookingDetailsService {
//
//	@Autowired
//	BookingDetailsRepository bookingDetailsRepository;
//
//	@Autowired
//	FacilityRepository facilityRepository;
//
//	@Autowired
//	MongoOperations mongoOperations;
//
//	@Autowired
//	TimeSlotsRepository timeSlotRepository;
//
//	@Override
//	public ResponseEntity<MessageResponse> bookFacility(BookingDetails bookingDetails) {
//		bookingDetails.setId(UUID.randomUUID().toString());
//		List<TimeSlots> timeSlots = checkAvailability(bookingDetails.getFacility(), bookingDetails.getBookingDate(),
//				bookingDetails.getBookingSlot());
//		if (!timeSlots.contains(bookingDetails.getBookingSlot())) {
//			System.out.println("Lets see the date: "+ bookingDetails.getBookingDate());
//			return ResponseEntity.badRequest().body(new MessageResponse("Facility not available for this slot"));
//		}
//
//		else {
//			//bookingDetails.getBookingDate().toInstant().
////			
////			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
////
////			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
////
////			String date12 = outputFormat.format(bookingDetails.getBookingDate());
////			
////			SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
////			
////			try {
////				Date date123;
////				date123 = inputFormat1.parse(date12.replace('"', ' '));
////				bookingDetails.setBookingDate(date123);
////			} catch (ParseException e) {
////				
////				e.printStackTrace();
////			}
//			
//			
//			
//			System.out.println("Lets see the date: "+ bookingDetails.getBookingDate());
//			String bookingId = bookingDetailsRepository.save(bookingDetails).getId();
//			return ResponseEntity.ok()
//					.body(new MessageResponse("Facility has been booked for you with booking Id: " + bookingId));
//		}
//
//	}
//
//	@Override
//	public List<TimeSlots> checkAvailability(Facilities facility, Date bookingDate, TimeSlots timeSlot) {
//
//		List<BookingDetails> bookingDetais = bookingDetailsRepository.findByFacilityAndBookingDateAndBookingSlot(facility,
//				bookingDate,timeSlot);
//		List<TimeSlots> timeslots = timeSlotRepository.findAll();
//		List<String> checkSameList = new ArrayList<String>();
//
//		// to check for same date, same slot, same facility name
//		for (BookingDetails bookingDetail : bookingDetais) {
//			String checkSame = bookingDetail.getFacility().getName() + " " + bookingDetail.getBookingDate() + " "
//					+ bookingDetail.getBookingSlot();
//			checkSameList.add(checkSame);
//		}
//
//		if (facility.getId()==1 && checkSameList.size() == 2) {
//			// remove timslot from timeslot list
//			boolean removed =timeslots.remove(bookingDetais.get(0).getBookingSlot());
//			System.out.println("remvd: "+removed);
//		}
//		if (facility.getId()==2 && checkSameList.size() == 1) {
//			timeslots.remove(bookingDetais.get(0).getBookingSlot());
//		}
//
//		if (facility.getId()==3 && checkSameList.size() == 3) {
//			timeslots.remove(bookingDetais.get(0).getBookingSlot());
//		}
//		return timeslots;
//	}
//
//	
//	@Override
//	public List<TimeSlots> checkAvailabilityForListingScehdule(Facilities facility, Date bookingDate) {
//
//		List<BookingDetails> bookingDetais = bookingDetailsRepository.findByFacilityAndBookingDate(facility,
//				bookingDate);
//		List<TimeSlots> timeslots = timeSlotRepository.findAll();
//		List<TimeSlots> checkSameList = new ArrayList<TimeSlots>();
//
//		// to check for same date, same slot, same facility name
//		for (BookingDetails bookingDetail : bookingDetais) {
//			TimeSlots checkSame = bookingDetail.getBookingSlot();
//			checkSameList.add(checkSame);
//			
//		}
//
//		if (facility.getId()==1 && checkSameList.size() >= 2) {
//
//			for(TimeSlots ts : timeslots) {
//				if(Collections.frequency(checkSameList, ts)>=2) {
//					boolean removed =timeslots.remove(ts);
//					break;
//				}
//			}
//			
//			
//
//		}
//		if (facility.getId()==2 && checkSameList.size() >= 1) {
//
//			for(TimeSlots ts : timeslots) {
//				if(Collections.frequency(checkSameList, ts)>=1) {
//					boolean removed =timeslots.remove(ts);
//					break;
//				}
//			}
//		}
//
//		if (facility.getId()==3 && checkSameList.size() >= 3) {
//
//			for(TimeSlots ts : timeslots) {
//				if(Collections.frequency(checkSameList, ts)>=3) {
//					boolean removed =timeslots.remove(ts);
//					break;
//				}
//			}
//		}
//		return timeslots;
//	}
//	public int generateSequence(String seqName) {
//
//		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
//				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
//		return (int) (!Objects.isNull(counter) ? counter.getSeq() : 1);
//
//	}
//
//	@Override
//	public List<Facilities> getFacilities() {
//
//		return facilityRepository.findAll();
//	}
//
//	@Override
//	public List<BookingDetails> getBooking(String playerId) {
//		Players player = new Players();
//		player.setId(playerId);
//		List<BookingDetails> bookingList = bookingDetailsRepository.findByPlayer(player);
//		
//
////		for(BookingDetails booking : bookingList) {
////			try {
////				
//////				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//////
////				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
//////				Date date1 = inputFormat.parse(date.replace('"', ' '));
////				String date12 = outputFormat.format(booking.getBookingDate());
////			
////				
////				SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////				inputFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
////				Date date123 = inputFormat1.parse(date12.replace('"', ' '));
////				
////				
////				booking.setBookingDate(date123);
////			} catch (ParseException e) {
////				e.printStackTrace();
////			}
////		}
//		
//		return bookingList;
//		
//	}
//
//}
