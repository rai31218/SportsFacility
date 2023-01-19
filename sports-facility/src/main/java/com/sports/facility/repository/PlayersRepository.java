package com.sports.facility.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sports.facility.model.Players;

@Repository
public interface PlayersRepository extends MongoRepository<Players, String> {

	Players findByEmail(String email);

}
