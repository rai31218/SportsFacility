package com.sports.facility.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.facility.model.Country;
import com.sports.facility.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	
	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public List<Country> getCountries() {
		return countryRepository.findAll();
		
	}

}
