package com.sports.facility.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.sports.facility.utility.ValidDate;
import com.sports.facility.utility.ValidPassword;

@Document(collection = "players")
public class Players {
	@Id
	private String id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

//	@NotNull(message = "DOB is mandatory")
//	@ValidDate
	private Date dob;

	@NotBlank
	@ValidPassword
	private String password;

	@NotBlank
	@Pattern(regexp = ".+.+@.+\\..+", message = "Please provide a valid email address")
	private String email;

	private boolean active;
	private int age;

	@DocumentReference(lazy = true)
	private Address address;

	//@Pattern(regexp = "\\d{10}", message = "Phone number should be of atleast 10 digits")
	private String contact;
	private Date registeredDate;

	public Players() {
		super();

	}

	public Players(String id, String firstName, String lasttName, Date dob, String password, String email,
			boolean active, int age, Address address, String contact, Date registeredDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lasttName;
		this.dob = dob;
		this.password = password;
		this.email = email;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
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

	public void setRegisteredDate(Date localDate) {
		this.registeredDate = localDate;
	}

}
