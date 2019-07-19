package com.bmt.SageClient.api_dataTypes;

public class SageInterfaceConnection {

	private boolean isSageInterfaceConnected;
	private String ipAddress;
	private String hostName;
	
	
	public boolean isSageInterfaceConnected() {
		return isSageInterfaceConnected;
	}
	public void setSageInterfaceConnected(boolean isSageInterfaceConnected) {
		this.isSageInterfaceConnected = isSageInterfaceConnected;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
}
