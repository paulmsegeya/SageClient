package com.bmt.SageClient.orm.dao;

import java.util.List;

import com.bmt.SageClient.sage200api.entities.CustomerViews;

public interface SageAPIHandlerDAO 
{
	
	public List<CustomerViews> requestCustomerViews(String customerName);

}
