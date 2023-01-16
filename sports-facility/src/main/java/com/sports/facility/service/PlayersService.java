package com.sports.facility.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sports.facility.dto.PlayersDTO;
import com.sports.facility.model.Players;

@Service
public interface PlayersService {

	String saveUser(PlayersDTO user);

	Optional<Players> findByPlayerId(String id);

	Players duplicateUserNameAndEmail(String email);

	Players updatePlayerDetails(PlayersDTO player, String playerId);

	Optional<Players> getDetails(String playerId);

	
	

}
