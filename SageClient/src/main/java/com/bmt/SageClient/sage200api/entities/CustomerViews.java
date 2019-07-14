package com.bmt.SageClient.sage200api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerViews 
{	
	
	private long id;
	private String reference;    
	private String name;    
	private String shortName;
	private String analysisCode1;
	private String analysisCode2;
	private String analysisCode3;
	private String telephoneSubscriberNumber;
	

	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

	@JsonProperty("reference")
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	

	@JsonProperty("name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	@JsonProperty("short_name")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	

	@JsonProperty("analysis_code_1")
	public String getAnalysisCode1() {
		return analysisCode1;
	}
	public void setAnalysisCode1(String analysisCode1) {
		this.analysisCode1 = analysisCode1;
	}

	@JsonProperty("analysis_code_2")
	public String getAnalysisCode2() {
		return analysisCode2;
	}
	public void setAnalysisCode2(String analysisCode2) {
		this.analysisCode2 = analysisCode2;
	}
	
	@JsonProperty("analysis_code_3")
	public String getAnalysisCode3() {
		return analysisCode3;
	}
	public void setAnalysisCode3(String analysisCode3) {
		this.analysisCode3 = analysisCode3;
	}

	@JsonProperty("telephone_subscriber_number")
	public String getTelephoneSubscriberNumber() {
		return telephoneSubscriberNumber;
	}
	public void setTelephoneSubscriberNumber(String telephoneSubscriberNumber) {
		this.telephoneSubscriberNumber = telephoneSubscriberNumber;
	}
	
	
	

}
