package com.bmt.SageClient.sage200api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerViews 
{	
	
	private int id;
	private String reference;    
	private String name;    
	private String short_name;    
	private int country_code_id;
	private String country_code;    
	private String currency_id;
	private String currency_name;    
	private int currency_iso_code_id;
	private String currency_iso_code;    
	private String currency_iso_code_name;  
	private double balance;
	private double base_balance;
	private double credit_limit;
	private String vat_number;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public int getCountry_code_id() {
		return country_code_id;
	}
	public void setCountry_code_id(int country_code_id) {
		this.country_code_id = country_code_id;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}
	public String getCurrency_name() {
		return currency_name;
	}
	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}
	public int getCurrency_iso_code_id() {
		return currency_iso_code_id;
	}
	public void setCurrency_iso_code_id(int currency_iso_code_id) {
		this.currency_iso_code_id = currency_iso_code_id;
	}
	public String getCurrency_iso_code() {
		return currency_iso_code;
	}
	public void setCurrency_iso_code(String currency_iso_code) {
		this.currency_iso_code = currency_iso_code;
	}
	public String getCurrency_iso_code_name() {
		return currency_iso_code_name;
	}
	public void setCurrency_iso_code_name(String currency_iso_code_name) {
		this.currency_iso_code_name = currency_iso_code_name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBase_balance() {
		return base_balance;
	}
	public void setBase_balance(double base_balance) {
		this.base_balance = base_balance;
	}
	public double getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(double credit_limit) {
		this.credit_limit = credit_limit;
	}
	public String getVat_number() {
		return vat_number;
	}
	public void setVat_number(String vat_number) {
		this.vat_number = vat_number;
	} 
	
	
	

}
