package com.book.facility.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import com.book.facility.BookFacilityApplication;
import com.book.facility.model.BookingDetails;
import com.book.facility.model.Facilities;
import com.book.facility.response.MessageResponse;
import com.book.facility.service.BookingDetailsService;


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
				+ "    \"dob\":\"{{CurrentDatetime}}\"\r\n"
				+ "    },\r\n"
				+ "    \"facility\":{\r\n"
				+ "        \"id\":\"1\",\r\n"
				+ "        \"name\":\"Cricket\",\r\n"
				+ "        \"initialAvailability\":\"1\"\r\n"
				+ "    },\r\n"
				+ "    \"bookingDate\":\"{{CurrentDatetime}}\",\r\n"
				+ "    \"bookingSlot\":{\r\n"
				+ "        \"id\":2,\r\n"
				+ "        \"timing\":\"7AM-8AM\"\r\n"
				+ "    }    \r\n"
				+ "}"))
		.andExpect(status().isOk());
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
//
//	@Test
//	void testAvailableSchedule() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetBookings() {
//		fail("Not yet implemented");
//	}

}
