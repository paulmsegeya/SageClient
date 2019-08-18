
package com.bmt.SageClient.api_dataTypes;

public class Telephone {
	
	private String telephone;
	private long id;
	private long customerContactID;
	private long customerID;
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
