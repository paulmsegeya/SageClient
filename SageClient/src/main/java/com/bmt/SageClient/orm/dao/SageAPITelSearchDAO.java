package com.bmt.SageClient.orm.dao;

import java.util.List;

import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;
import com.bmt.SageClient.sage200api.entities.CustomerTelephones;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;

public interface SageAPITelSearchDAO {
		
	public List<String> seachViaTel(String tel);
	public List<CustomerTelephones> getContactIDViaTel(String tel);
	public List<Customers> getCustomers(List<CustomersContacts> customerContacts);
}
