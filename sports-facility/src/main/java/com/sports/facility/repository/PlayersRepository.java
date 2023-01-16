package com.sports.facility.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sports.facility.model.Players;

@Repository
public interface PlayersRepository extends MongoRepository<Players, String> {

	Players findByEmail(String email);
	
//	   @Query(value = "{ '_id' : ?0 }", fields = "{ '_id' : 1}")
//	   Optional<Players> findById(String id);
    
}
