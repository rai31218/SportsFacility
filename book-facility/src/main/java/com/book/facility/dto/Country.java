package com.book.facility.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "country")
public class Country {

	@Id
	private int id;
	private String name;
	
	@DocumentReference(lazy=false)
	private List<State> state;
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(int id, String name, List<State> state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<State> getState() {
		return state;
	}
	public void setState(List<State> state) {
		this.state = state;
	}
	
	
}
