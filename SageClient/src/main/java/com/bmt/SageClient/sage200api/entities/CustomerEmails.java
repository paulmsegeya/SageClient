package com.bmt.SageClient.sage200api.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerEmails 
{
	
	private long id;
	private String email;
	private String customerContactID;
	
	

	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonProperty("customer_contact_id")
	public String getCustomerContactID() {
		return customerContactID;
	}
	public void setCustomerContactID(String customerContactID) {
		this.customerContactID = customerContactID;
	}
	

	
	

}
