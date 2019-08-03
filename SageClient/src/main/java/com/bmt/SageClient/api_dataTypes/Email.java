package com.bmt.SageClient.api_dataTypes;

public class Email {
	
	private String email;
	private long id;
	private long customerContactID;
	private long customerID;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerContactID() {
		return customerContactID;
	}
	public void setCustomerContactID(long customerContactID) {
		this.customerContactID = customerContactID;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

}
