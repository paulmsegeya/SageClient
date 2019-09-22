package com.bmt.SageClient.sage200api.entities;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomersContacts 
{
	
	private long id;
	private long customerID;
	private String name;
	private String firstName;
	private String middleName;
	private String lastName;
	private String defaultTelephone;
	private String defaultEmail;
	
	

	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonProperty("customer_id")
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("middle_name")
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonProperty("default_telephone")
	public String getDefaultTelephone() {
		return defaultTelephone;
	}
	public void setDefaultTelephone(String defaultTelephone) {
		this.defaultTelephone = defaultTelephone;
	}

	@JsonProperty("default_email")
	public String getDefaultEmail() {
		return defaultEmail;
	}
	public void setDefaultEmail(String defaultEmail) {
		this.defaultEmail = defaultEmail;
	}

}
