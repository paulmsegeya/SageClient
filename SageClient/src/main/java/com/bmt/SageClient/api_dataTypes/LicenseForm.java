package com.bmt.SageClient.api_dataTypes;

import java.sql.Timestamp;

public class LicenseForm 
{
	
	private Timestamp startDate;
	private Timestamp endDate;
	private String distributionID;
	
	
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getDistributionID() {
		return distributionID;
	}
	public void setDistributionID(String distributionID) {
		this.distributionID = distributionID;
	}
	
}
