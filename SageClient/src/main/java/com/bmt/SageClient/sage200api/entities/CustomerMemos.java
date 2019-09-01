package com.bmt.SageClient.sage200api.entities;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerMemos 
{
	
	private long id;
	private long customerId;
	private String note;
	private Date dateTimeUpdated;
	

	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("customer_id")
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("note")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@JsonProperty("date_time_updated")
	public Date getDateTimeUpdated() {
		return dateTimeUpdated;
	}
	public void setDateTimeUpdated(Date dateTimeUpdated) {
		this.dateTimeUpdated = dateTimeUpdated;
	}
	
	

}
