//package com.sports.facility.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sports.facility.SportsFacilityApplication;
//import com.sports.facility.dto.PlayersDTO;
//import com.sports.facility.model.Address;
//import com.sports.facility.model.Country;
//import com.sports.facility.model.Players;
//import com.sports.facility.model.State;
//import com.sports.facility.service.CountryService;
//import com.sports.facility.service.PlayersService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { SportsFacilityApplication.class })
//class PlayersControllerTest {
//
//	private MockMvc mockMvc;
//
//	private static MockHttpServletRequest request;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@MockBean
//	private PlayersService playersServiceMock;
//
//	@MockBean
//	private AuthenticationManager authenticationManager;
//
//	@MockBean
//	RestTemplate restTemplate;
//
//	@MockBean
//	private CountryService countryServiceMock;
//
//	private MockRestServiceServer mockServer;
//	private ObjectMapper mapper = new ObjectMapper();
//
//	
//	@BeforeAll
//	public static void setup() {
//		request = new MockHttpServletRequest();
//		State st1 = new State(1, "West Bengal");
//		List stl= new ArrayList();
//		stl.add(st1);
//		Country ct1 = new Country(1, "India", stl);
//		Address ad1= new Address("Haltu", st1, ct1);
//		
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
//		c.add(Calendar.YEAR, 18);
//		Date newDate = c.getTime();
//		
//		request.setParameter("firstName", "Ishani");
//		request.setParameter("lastName", "Sen");
//		request.setParameter("password", "pwd12345");
//		request.setParameter("email", "ruddy@gmail.com");
//		request.setParameter("address", ad1.toString());
//		request.setParameter("pan", "bhghgdewtw");
//		request.setParameter("contact", "9897544321");
//		request.setParameter("dob", newDate.toString());
//	}
//	@BeforeEach
//	public void setUp() {
//		this.mockMvc = webAppContextSetup(webApplicationContext).build();
//	}
//
//	@Test
//	void testSignUp() throws Exception {
//
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
//		c.add(Calendar.YEAR, 18);
//		Date newDate = c.getTime();
//
//		PlayersDTO pl1 = new PlayersDTO();
//		pl1.setDob(newDate);
//		Date  d=pl1.getDob();
//		long d1=d.getTime();
//		int age=19;
//		
//		String savedId = "23de-ygt-5776";
//		when(playersServiceMock.calculateAge(d1)).thenReturn(age);
//		when(playersServiceMock.duplicateUserNameAndEmail("ruddy@gmail.com")).thenReturn(null);
//		when(playersServiceMock.saveUser(any(PlayersDTO.class))).thenReturn(savedId);
//		
//
//		mockMvc.perform(post("/sports/sign-up").contentType(MediaType.APPLICATION_JSON)
//				.content("{   \"firstName\": \"Ishani\",\r\n"
//						+ "    \"lastName\": \"Sen\",\r\n"
//						+ "    \"password\":\"pwd@C22345\",\r\n"
//						+ "    \"email\":\"ish.au@gmail.com\"\r\n"
//						+ "    \"dob\":\"27-10-1995\"\r\n"
//						+ "\r\n"
//						+ "}"))
//				.andExpect(status().isBadRequest());
//		// .andExpect(content().json("{\"message\": \"User registered
//		// successfully!\"}"));
//		// ("message": "User registered successfully!"));
//		
////		mockMvc.perform(post("/sports/sign-up").contentType(MediaType.APPLICATION_JSON)
////				.param("firstName", request.getParameterValues("firstName"))
////				.param("lastName", request.getParameterValues("lastName"))
////				.param("email", request.getParameterValues("email"))
////				.param("password", request.getParameterValues("password"))
////				.param("address", request.getParameterValues("address"))
////				.param("pan", request.getParameterValues("pan"))
////				.param("contact", request.getParameterValues("contact"))
////				.param("dob", newDate)
////				)
////		.andExpect(status().isCreated());
//
//	}
//
////	@Test
////	void testSignIn() {
////		fail("Not yet implemented");
////	}
////
////	@Test
////	void testGetPlayerDetails() {
////		fail("Not yet implemented");
////	}
////
//
//	@Test
//	public void testUpdatePlayerDetails() throws Exception {
//
//		Players player = new Players();
//		player.setFirstName("Rudra");
//		when(playersServiceMock.updatePlayerDetails(any(PlayersDTO.class), eq("MS-687"))).thenReturn(player);
//		mockMvc.perform(put("/sports/update/MS-687").contentType(MediaType.APPLICATION_JSON)
//				.content("{   \"firstName\": \"Ishani\",\r\n"
//						+ "    \"lastName\": \"Sen\",\r\n"
//						+ "    \"password\":\"pwd22345\",\r\n"
//						+ "    \"email\":\"ishani@gmail.com\",\r\n"
//						+ "    \"contact\": 9163511193  \r\n"
//						+ "}"))
//				.andExpect(status().isOk());
//
//	}
//
//	@Test
//	public void testGetCountries() throws Exception {
//		List<Country> countryList = new ArrayList();
//		when(countryServiceMock.getCountries()).thenReturn(countryList);
//
//		mockMvc.perform(get("/sports/getcountries")).andExpect(status().isOk());
//	}
//
//}
