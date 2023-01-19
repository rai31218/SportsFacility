package com.book.facility.dto;

import java.util.List;




public class Address {
	

	private int id;
	private String homeAddress;
	

	private State state;

	private Country  country;
	

    List<PlayersDTO> players;
	
	
	
	public Address(String homeAddress ,State state, Country country) {
		super();
		this.homeAddress = homeAddress;
		this.state = state;
		this.country = country;
	}
	
	public Address(int id, String homeAddress, State state, Country country, List<PlayersDTO> players) {
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

	public List<PlayersDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayersDTO> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", homeAddress=" + homeAddress + ", state=" + state + ", country=" + country
				+ ", players=" + players + "]";
	}



}
