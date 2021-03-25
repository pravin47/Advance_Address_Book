package com.advance.address.book;

import java.io.Serializable;
import java.util.Scanner;

class Book implements Serializable {

	int id;

	String firstname = "";

	String lastname = "";

	String typename = "";

	String city = "";

	String state = "";

	String zip_code = "";

	String phone_no = "";

	String email = "";

	public Book(int id, String firstname, String lastname, String typename, String city, String state, String zip_code,
			String phone_no, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.typename = typename;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.phone_no = phone_no;
		this.email = email;
	}

	public Book() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return "Book [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", typename=" + typename
				+ ", city=" + city + ", state=" + state + ", zip_code=" + zip_code + ", phone_no=" + phone_no
				+ ", email=" + email + "]";
	}

}