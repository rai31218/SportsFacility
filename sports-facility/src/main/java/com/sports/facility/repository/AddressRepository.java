package com.sports.facility.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sports.facility.model.Address;
import com.sports.facility.model.DatabaseSequence;

public interface AddressRepository extends MongoRepository<Address, Integer> {

	DatabaseSequence findTopByOrderByIdDesc();

}
