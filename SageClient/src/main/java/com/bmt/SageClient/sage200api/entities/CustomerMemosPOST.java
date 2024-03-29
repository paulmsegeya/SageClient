package com.bmt.SageClient.sage200api.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerMemosPOST 
{
	private long customerId;
	private String note;
	

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
	
	

}
