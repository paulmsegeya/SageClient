package com.bmt.SageClient.sage200api.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomersContacts 
{
	
	private long id;
	private String name;
	private String defaultTelephone;
	private String defaultEmail;
	
	

	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
