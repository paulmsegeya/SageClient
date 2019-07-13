package com.bmt.SageClient.api_dataTypes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerInfoFields {
	
	private Map<String, String> fieldNames;
	private List<String> fieldAPINames;
	private List<String> fieldDisplayNames;

	public CustomerInfoFields()
	{
		fieldNames = new LinkedHashMap<>();
		fieldAPINames = new ArrayList<>();		
		fieldDisplayNames = new ArrayList<>();
		setFieldNames();
		setFieldDisplayNames();
		setFieldAPINames();
	}	
	
	
	public Map<String, String> getFieldNames() {
		return fieldNames;
	}
	public void setFieldNames(Map<String, String> fieldNames) {
		this.fieldNames = fieldNames;
	}
	
	
	public List<String> getFieldDisplayNames() {
		return fieldDisplayNames;
	}


	public void setFieldDisplayNames(List<String> fieldDisplayNames) {
		this.fieldDisplayNames = fieldDisplayNames;
	}
	
	public List<String> getFieldAPINames() {
		return fieldAPINames;
	}


	public void setFieldAPINames(List<String> fieldAPINames) {
		this.fieldAPINames = fieldAPINames;
	}


	private void setFieldDisplayNames()
	{
		fieldDisplayNames.add("Account Ref");
		fieldDisplayNames.add("Account Name");
		fieldDisplayNames.add("Short Name");
		fieldDisplayNames.add("Customer Name");
		fieldDisplayNames.add("Date of Shoot");
		fieldDisplayNames.add("Package");
		fieldDisplayNames.add("Total");
		fieldDisplayNames.add("Invoice Balance");
		fieldDisplayNames.add("Signed Date");
		fieldDisplayNames.add("Type");
		fieldDisplayNames.add("Email");
		fieldDisplayNames.add("Email 2");
		fieldDisplayNames.add("Tel");
		fieldDisplayNames.add("Seen Contact");
		fieldDisplayNames.add("Notes");
	}


	private void setFieldAPINames()
	{
		fieldAPINames.add("accountRef");
		fieldAPINames.add("accountName");
		fieldAPINames.add("shortName");
		fieldAPINames.add("customerName");
		fieldAPINames.add("dateOfShoot");
		fieldAPINames.add("transPackage");
		fieldAPINames.add("total");
		fieldAPINames.add("invoiceBalance");
		fieldAPINames.add("signedDate");
		fieldAPINames.add("type");
		fieldAPINames.add("email");
		fieldAPINames.add("email2");
		fieldAPINames.add("tel");
		fieldAPINames.add("seenContact");
		fieldAPINames.add("notes");
	}


	private void setFieldNames()
	{
		fieldNames.put("accountRef", "Account Ref");
		fieldNames.put("accountName", "Account Name");
		fieldNames.put("shortName", "Short Name");
		fieldNames.put("customerName", "Customer Name");
		fieldNames.put("dateOfShoot", "Date of Shoot");
		fieldNames.put("transPackage", "Package");
		fieldNames.put("total", "Total");
		fieldNames.put("invoiceBalance", "Invoice Balance");
		fieldNames.put("signedDate", "Signed Date");
		fieldNames.put("type", "Type");
		fieldNames.put("email", "Email");
		fieldNames.put("email2", "Email 2");
		fieldNames.put("tel", "Tel");
		fieldNames.put("seenContact", "Seen Contact");
		fieldNames.put("notes", "Notes");
	}
	
}
