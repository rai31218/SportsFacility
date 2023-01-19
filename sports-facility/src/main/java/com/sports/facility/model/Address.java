package com.sports.facility.model;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "address")
public class Address {
	
	@Transient
	public static final String SEQUENCE_NAME = "address_sequence";
	
	@Id
	private int id;
	private String homeAddress;
	
	@DocumentReference(lazy=true)
	private State state;
	@DocumentReference(lazy=true)
	private Country  country;
	
	@DocumentReference
    List<Players> players;
	
	
	
	public Address(String homeAddress ,State state, Country country) {
		super();
		this.homeAddress = homeAddress;
		this.state = state;
		this.country = country;
	}
	
	public Address(int id, String homeAddress, State state, Country country, List<Players> players) {
		super();
		this.id = id;
		this.homeAddress = homeAddress;

		this.state = state;
		this.country = country;
		this.players = players;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Players> getPlayers() {
		return players;
	}

	public void setPlayers(List<Players> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", homeAddress=" + homeAddress + ", state=" + state + ", country=" + country
				+ ", players=" + players + "]";
	}



}
