package com.bmt.SageClient.sage200api.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sites 
{
	
	private int company_id;
	private String company_name;
	private String site_id;
	private String site_name;
	private String site_short_name;
	
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getSite_short_name() {
		return site_short_name;
	}
	public void setSite_short_name(String site_short_name) {
		this.site_short_name = site_short_name;
	}

}
