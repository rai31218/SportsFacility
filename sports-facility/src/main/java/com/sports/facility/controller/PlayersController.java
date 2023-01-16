package com.sports.facility.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.facility.dto.PlayersDTO;
import com.sports.facility.model.Players;
import com.sports.facility.response.MessageResponse;
import com.sports.facility.security.AuthRequest;
import com.sports.facility.security.JwtResponse;
import com.sports.facility.security.JwtUtil;
import com.sports.facility.security.UserDetailsImpl;
import com.sports.facility.service.CountryService;
import com.sports.facility.service.PlayersService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://digitalbook-frontend.s3.amazonaws.com" })
@RequestMapping(value = "/sports")
public class PlayersController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	PlayersService playersService;

	@Autowired
	CountryService countryService;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody PlayersDTO player) {
		try {

			LocalDate localDate = Instant.ofEpochMilli(player.getDob().getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();
			int age = Period.between(localDate, LocalDate.now()).getYears();

			Players duplicateUser = playersService.duplicateUserNameAndEmail(player.getEmail());

			if (duplicateUser != null) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Player with same email id already exists!"));
			}
//		else if (age < 18) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Age must be greater than 18!"));
//		} 
			else {
				String savedId = playersService.saveUser(player);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new MessageResponse("Player registered successfully with Player Id - " + savedId));

			}
		} catch (Exception e) {

			return ResponseEntity.badRequest()
					.body(new MessageResponse("Something went wrong!!! Please try again with proper input"));

		}

	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest, HttpServletResponse httpServletResponse,
			HttpSession session) throws Exception {
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateToken(authRequest.getEmail());
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getEmail(), userDetails.getId()));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(new MessageResponse("Invalid username or password!"));

		}

	}

	@GetMapping("/getDetails/{player-id}")
	public ResponseEntity<?> getPlayerDetails(@PathVariable("player-id") String playerId) {
		Optional<Players> updatedPlayer = playersService.getDetails(playerId);
		if (updatedPlayer.isPresent()) {
			return ResponseEntity.ok(updatedPlayer);
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Player is not present"));
	}

	@PutMapping("/update/{player-id}")
	public ResponseEntity<?> updatePlayerDetails(@RequestBody PlayersDTO player,
			@PathVariable("player-id") String playerId) {
		Players updatedPlayer = playersService.updatePlayerDetails(player, playerId);
		if (updatedPlayer != null) {
			return ResponseEntity.ok(updatedPlayer);
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Player is not present"));

	}

	@GetMapping("/getcountries")
	public ResponseEntity<?> getCountries() {
		return ResponseEntity.ok(countryService.getCountries());
	}
}