package com.bmt.SageClient.api_dataTypes;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SageConnectionTest 
{
	private String logonName;
	private String firendlyName;
	private String emailAddress;
	private boolean isConnectedToSage;
	private ServerResponse serverResponse;
	
	@JsonProperty("logon_name")
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	
	@JsonProperty("friendly_name")
	public String getFirendlyName() {
		return firendlyName;
	}
	public void setFirendlyName(String firendlyName) {
		this.firendlyName = firendlyName;
	}
	
	@JsonProperty("email_address")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public boolean isConnectedToSage() {
		return isConnectedToSage;
	}
	public void setConnectedToSage(boolean isConnectedToSage) {
		this.isConnectedToSage = isConnectedToSage;
	}
	public ServerResponse getServerResponse() {
		return serverResponse;
	}
	public void setServerResponse(ServerResponse serverResponse) {
		this.serverResponse = serverResponse;
	}

}
