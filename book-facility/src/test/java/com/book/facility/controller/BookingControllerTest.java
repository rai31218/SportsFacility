package com.book.facility.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import com.book.facility.BookFacilityApplication;
import com.book.facility.dto.AvailabilityDTO;
import com.book.facility.dto.PlayersDTO;
import com.book.facility.model.BookingDetails;
import com.book.facility.model.Facilities;
import com.book.facility.model.TimeSlots;
import com.book.facility.response.MessageResponse;
import com.book.facility.service.BookingDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ BookFacilityApplication.class })
class BookingControllerTest {
	
	private MockMvc mockMvc;
	
	@MockBean
	private BookingDetailsService bookingServiceMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void testBookFacility() throws Exception {
		ResponseEntity response =new ResponseEntity(HttpStatus.OK).ok(new MessageResponse("Facility not available for this slot"));
		//ResponseEntity.ok(new MessageResponse("Facility not available for this slot"));
        when(bookingServiceMock.bookFacility(any(BookingDetails.class))).thenReturn(response);

       mockMvc.perform(post("/sports/bookfacility")
		.contentType(MediaType.APPLICATION_JSON).content("{\r\n"
				+ "    \"player\":{\r\n"
				+ "    \"id\":\"MS-241\",\r\n"
				+ "    \"firstName\": \"Rudra\",\r\n"
				+ "    \"lastName\": \"Rai\",\r\n"
				+ "    \"password\":\"pwd1\",\r\n"
				+ "    \"email\":\"rai@gmail.com\",\r\n"
				+ "    \"address\":{\r\n"
				+ "        \"homeAddress\":\"Kailash\",\r\n"
				+ "        \"state\":{\r\n"
				+ "            \"id\":1,\r\n"
				+ "             \"name\":\"West Bengal\"}\r\n"
				+ "    },\r\n"
				+ "    \"dob\":27-01-1995\r\n"
				+ "    },\r\n"
				+ "    \"facility\":{\r\n"
				+ "        \"id\":\"1\",\r\n"
				+ "        \"name\":\"Cricket\",\r\n"
				+ "        \"initialAvailability\":\"1\"\r\n"
				+ "    },\r\n"
				+ "    \"bookingDate\":27-03-2023,\r\n"
				+ "    \"bookingSlot\":{\r\n"
				+ "        \"id\":2,\r\n"
				+ "        \"timing\":\"7AM-8AM\"\r\n"
				+ "    }    \r\n"
				+ "}"))
		.andExpect(status().isBadRequest());
				//("message": "User registered successfully!"));
	}

	@Test
	public void testGetFacilities() throws Exception {
           Facilities f1= new Facilities(1, "a", "a", 1);
           Facilities f2= new Facilities(2, "b", "b", 2);
           Facilities f3= new Facilities(3, "c", "c", 3);
           
           List<Facilities> fList = new ArrayList<Facilities>();
           fList.add(f1);
           fList.add(f2);
           fList.add(f3);
   		ResponseEntity response =new ResponseEntity(HttpStatus.OK).ok(fList);
   		//ResponseEntity.ok(new MessageResponse("Facility not available for this slot"));
           when(bookingServiceMock.bookFacility(any())).thenReturn(response);

	       mockMvc.perform(get("/sports/getfacilities"))
	       .andExpect(status().isOk());
	       
	}

	@Test
	public void testAvailableSchedule() throws Exception {
		 TimeSlots t1= new TimeSlots(1, "6AM-7AM");
		 TimeSlots t2= new TimeSlots(2, "7AM-8AM");
		 TimeSlots t3= new TimeSlots(3, "8AM-9AM");
		 
		 List<TimeSlots> tlist= new ArrayList<TimeSlots>();
		 tlist.add(t1);
		 tlist.add(t2);
		 tlist.add(t3);
		
		 
		 String strdate = "2023-01-27T 12:00:00.000Z";
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
			Date date1 = inputFormat.parse(strdate.replace('"', ' '));
			String date12 = outputFormat.format(date1);
		
			
			SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss+ss");
			inputFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date date123 = inputFormat1.parse(date12.replace('"', ' '));
		 
			AvailabilityDTO avilabilitycheck = new AvailabilityDTO(date123, new Facilities(1, "Cricket"));
			
			when(bookingServiceMock
			.checkAvailabilityForListingScehdule( any(),any()))
			.thenReturn(tlist);

		 
		 ResultActions rs = mockMvc.perform(get("/sports/availability/"+1+"/Cricket/"+strdate));
	    //   .andExpect(content().json(convertObjectToJsonString(tlist)));
		 rs.andExpect(content().json("[{\r\n"
		 		+ "        \"id\": 1,\r\n"
		 		+ "        \"timing\": \"6AM-7AM\"\r\n"
		 		+ "    },\r\n"
		 		+ "    {\r\n"
		 		+ "        \"id\": 2,\r\n"
		 		+ "        \"timing\": \"7AM-8AM\"\r\n"
		 		+ "    },\r\n"
		 		+ "    {\r\n"
		 		+ "        \"id\": 3,\r\n"
		 		+ "        \"timing\": \"8AM-9AM\"\r\n"
		 		+ "    }]"));
	
	}

	@Test
	public void testGetBookings() throws Exception {
	BookingDetails bs = new BookingDetails("1", new PlayersDTO(), new Date(),new TimeSlots(1, "6AM-7AM")
			,new Facilities(1, "a", "a", 1));
	
	List<BookingDetails> bsList = new ArrayList();
	
	bsList.add(bs);
		when(bookingServiceMock
				.getBooking("1"))
				.thenReturn(bsList);	
		
		mockMvc.perform(get("/sports/getBookings/1"))
		.andExpect(jsonPath("$[*].id").value("1"));
		
	}

	private String convertObjectToJsonString(List<TimeSlots> studentList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(studentList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
