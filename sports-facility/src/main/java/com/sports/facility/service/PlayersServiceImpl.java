package com.sports.facility.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sports.facility.dto.PlayersDTO;
import com.sports.facility.model.Address;
import com.sports.facility.model.DatabaseSequence;
import com.sports.facility.model.Players;
import com.sports.facility.repository.AddressRepository;
import com.sports.facility.repository.PlayersRepository;
import com.sports.facility.repository.SequenceRepository;

@Service
public class PlayersServiceImpl implements PlayersService {

	@Autowired
	PlayersRepository playerRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	SequenceRepository sequenceRepository;


	@Autowired
	PasswordEncoder encoder;

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public String saveUser(PlayersDTO playerdto) {
		try {

			Players player = new Players();
			Address address = new Address(playerdto.getAddress().getHomeAddress(), playerdto.getAddress().getState(),
					playerdto.getAddress().getCountry());
			address.setId(generateSequence(Address.SEQUENCE_NAME));
			addressRepository.save(address);

			LocalDate localDate = Instant.ofEpochMilli(playerdto.getDob().getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();
			player.setId("MS-" + (new Random().nextInt(1000 - 100) + 100));
			player.setEmail(playerdto.getEmail());
			player.setPassword(encoder.encode(playerdto.getPassword()));
			player.setRegisteredDate(new Date());
			player.setActive(true);
			player.setContact(String.valueOf(playerdto.getContact()));
			player.setAddress(address);
			player.setAge(Period.between(localDate, LocalDate.now()).getYears());
			player.setDob(playerdto.getDob());
			player.setFirstName(playerdto.getFirstName());
			player.setLastName(playerdto.getLastName());
			Players savedPlayer = playerRepository.save(player);
			return savedPlayer.getId();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Optional<Players> findByPlayerId(String id) {
		return playerRepository.findById(id);

	}

	@Override
	public Players duplicateUserNameAndEmail(String email) {

		return playerRepository.findByEmail(email);
	}

	@Override
	public Players updatePlayerDetails(PlayersDTO playerdto, String playerId) {
		Optional<Players> currentPlayer = getDetails(playerId);
		if (currentPlayer.isPresent()) {

			Optional<Address> address = addressRepository.findById(currentPlayer.get().getAddress().getId());
			if(address.isPresent()) {
				address.get().setState(playerdto.getAddress().getState());
				address.get().setHomeAddress(playerdto.getAddress().getHomeAddress());
				addressRepository.save(address.get());
			}
			
			currentPlayer.get().setEmail(playerdto.getEmail());
			currentPlayer.get().setContact(String.valueOf(playerdto.getContact()));

			

			Players updatedPlayer = playerRepository.save(currentPlayer.get());
			return updatedPlayer;
		} else {
			return null;
		}

	}

	@Override
	public Optional<Players> getDetails(String playerId) {
		Optional<Players> currentPlayer = playerRepository.findById(playerId);
		return currentPlayer;
	}

	public int generateSequence(String seqName) {

		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return (int) (!Objects.isNull(counter) ? counter.getSeq() : 1);

	}

	@Override
	public int calculateAge(long time) {
		LocalDate localDate = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
		int age = Period.between(localDate, LocalDate.now()).getYears();
		return age;
	}

}
