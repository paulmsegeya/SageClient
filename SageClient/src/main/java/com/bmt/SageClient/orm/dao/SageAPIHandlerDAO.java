package com.bmt.SageClient.orm.dao;

import java.util.List;

import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;
import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;

public interface SageAPIHandlerDAO 
{
	public CustomerInfo getCustomerInfo(String name);
	public List<Customers> requestCustomers(String name);
	public List<CustomerViews> requestCustomerViews(Long id);
	public List<CustomersContacts> requestCustomerContacts(Long id);
	public List<Transactions> requestTransactions(Long id);
	public List<CustomerMemos> requestMemos(Long id);

}
