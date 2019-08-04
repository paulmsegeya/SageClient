package com.bmt.SageClient.sage200api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerTelephones 
{
	
	private long id;
	private long customerContactID;
	private String fullNumber;
	private String subscriberNumber; 
	
	
	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonProperty("customer_contact_id")
	public long getCustomerContactID() {
		return customerContactID;
	}
	public void setCustomerContactID(long customerContactID) {
		this.customerContactID = customerContactID;
	}
	
	@JsonProperty("full_number")
	public String getFullNumber() {
		return fullNumber;
	}
	public void setFullNumber(String fullNumber) {
		this.fullNumber = fullNumber;
	}
	
	@JsonProperty("subscriber_number")
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

}
