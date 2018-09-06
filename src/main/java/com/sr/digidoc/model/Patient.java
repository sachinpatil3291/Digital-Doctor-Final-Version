package com.sr.digidoc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient extends BaseResponse implements Serializable {

	private static final long serialVersionUID = -75885815725314443L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;

	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;

	@Column(name="Address")
	private String address;

	@Column(name="Contact")
	private String contact;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="SFHC")
	private String SFHCCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSFHCCode() {
		return SFHCCode;
	}

	public void setSFHCCode(String sFHCCode) {
		SFHCCode = sFHCCode;
	}



}
