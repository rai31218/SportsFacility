package com.sports.facility.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sports.facility.model.Country;

public interface CountryRepository extends MongoRepository<Country, Integer> {

}
