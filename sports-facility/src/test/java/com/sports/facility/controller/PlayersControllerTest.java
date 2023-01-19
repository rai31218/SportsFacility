package com.sports.facility.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sports.facility.SportsFacilityApplication;
import com.sports.facility.dto.PlayersDTO;
import com.sports.facility.model.Country;
import com.sports.facility.model.Players;
import com.sports.facility.service.CountryService;
import com.sports.facility.service.PlayersService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ SportsFacilityApplication.class })
class PlayersControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	
	@MockBean
	private PlayersService playersServiceMock;
	
	@MockBean
	private AuthenticationManager authenticationManager;
	
	@MockBean
	RestTemplate restTemplate;
	
	@MockBean
	private CountryService countryServiceMock;

	 private MockRestServiceServer mockServer;
	 private ObjectMapper mapper = new ObjectMapper();
	    
		@BeforeEach
		public void setUp() {
			this.mockMvc = webAppContextSetup(webApplicationContext).build();
		}
	
	@Test
	void testSignUp() throws Exception {
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, 18);
		Date newDate = c.getTime();
		
		String savedId="23de-ygt-5776";
     when(playersServiceMock.duplicateUserNameAndEmail("ruddy@gmail.com")).thenReturn(null);
     when(playersServiceMock.saveUser(any(PlayersDTO.class))).thenReturn(savedId);
     when(playersServiceMock.calculateAge(any(Long.class))).thenReturn(19);
    
		mockMvc.perform(post("/sports/sign-up")
				.contentType(MediaType.APPLICATION_JSON).content("{\r\n"
						+ "    \r\n"
						+ "    \"firstName\": \"Ishani\",\r\n"
						+ "    \"lastName\": \"Sen\",\r\n"
						+ "    \"password\":\"pwd22345\",\r\n"
						+ "    \"email\":\"ruddy@gmail.com\",\r\n"
						+ "    \"address\":{\r\n"
						+ "        \"homeAddress\":\"Haltu\",\r\n"
						+ "        \"state\":{\r\n"
						+ "            \"id\":1,\r\n"
						+ "            \"name\":\"West Bengal\"\r\n"
						+ "        },\r\n"
						+ "        \"country\":{\r\n"
						+ "            \"id\":1,\r\n"
						+ "            \"name\":\"India\"\r\n"
						+ "        }\r\n"
						+ "    },\r\n"
						+ "    \"pan\":\"panbhgf\",\r\n"
						+ "    \"contact\": 9163511193,\r\n"
						+ "    \"dob\":"+newDate+"\"\r\n"
						+ "    \r\n"
						+ "}"))
				.andExpect(status().isBadRequest());
				//.andExpect(content().json("{\"message\": \"User registered successfully!\"}"));
		//("message": "User registered successfully!"));
	
	}

//	@Test
//	void testSignIn() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetPlayerDetails() {
//		fail("Not yet implemented");
//	}
//
	
	@Test
	public void testUpdatePlayerDetails() throws Exception {
		
		Players player = new Players();
		player.setFirstName("Rudra");
		when(playersServiceMock.updatePlayerDetails(any(PlayersDTO.class), eq("ghghgg"))).thenReturn(player);
		mockMvc.perform(put("/sports/update/1")
				.contentType(MediaType.APPLICATION_JSON).content("{\r\n"
						+ "    \r\n"
						+ "    \"email\":\"ishani@gmail.com\",\r\n"
						+ "    \"address\":{\r\n"
						+ "        \"homeAddress\":\"Haltu\",\r\n"
						+ "        \"state\":\"westbengal\"\r\n"
						+ "    },\r\n"
						+ "    \"pan\":\"panbhgf\",\r\n"
						+ "    \"contact\": 9163511193,\r\n"
						+ "    \r\n"
						+ "}"))
				.andExpect(status().isBadRequest());

		
	}

	@Test
	public void testGetCountries() throws Exception {
		List<Country> countryList = new ArrayList();
		when(countryServiceMock.getCountries()).thenReturn(countryList);
		
		mockMvc.perform(get("/sports/getcountries")).andExpect(status().isOk());
	}

}
