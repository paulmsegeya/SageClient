package com.bmt.SageClient.api_dataTypes;

public class User 
{
	private String logonName;
	private String firendlyName;
	private String emailAddress;
	private boolean isConnectedToSage;
	
	
	public String getLogonName() {
		return logonName;
	}
	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}
	public String getFirendlyName() {
		return firendlyName;
	}
	public void setFirendlyName(String firendlyName) {
		this.firendlyName = firendlyName;
	}
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

}
