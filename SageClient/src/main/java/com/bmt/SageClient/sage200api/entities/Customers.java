package com.bmt.SageClient.sage200api.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customers 
{
	
	private long id;
	private String reference;
	private String name;
	private String shortName;
	private String analysisCode2;
	
	

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
	public void setShort_name(String shortName) {
		this.shortName = shortName;
	}
	
	@JsonProperty("analysis_code_2")
	public String getAnalysisCode2() {
		return analysisCode2;
	}	
	@JsonProperty("analysis_code_2")
	public void setAnalysisCode2(String analysisCode2) {
		this.analysisCode2 = analysisCode2;
	}
	
	

}
