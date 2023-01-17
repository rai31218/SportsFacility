package com.sports.facility.dto;


import java.util.Date;

import com.sports.facility.model.Address;

public class PlayersDTO {
	private String id;
	private String firstName;
	private String lastName;
	private Date dob;
	private String password;
	private String email;
	//private String pan;
	private boolean active;
	private int age;
	private Address address;
	private long contact;
	private Date registeredDate;
	public PlayersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PlayersDTO(String id, String firstName, String lasttName, Date dob, String password, String email,
			boolean active, int age, Address address, long contact, Date registeredDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lasttName;
		this.dob = dob;
		this.password = password;
		this.email = email;
		//this.pan = pan;
		this.active = active;
		this.age = age;
		this.address = address;
		this.contact = contact;
		this.registeredDate = registeredDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lasttName) {
		this.lastName = lasttName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public String getPan() {
//		return pan;
//	}
//	public void setPan(String pan) {
//		this.pan = pan;
//	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	

}
