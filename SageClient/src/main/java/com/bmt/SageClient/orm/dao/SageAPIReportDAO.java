package com.bmt.SageClient.orm.dao;

import java.util.Date;
import java.util.List;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerTelephones;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;

public interface SageAPIReportDAO {
		
	public List<CustomerInfo> getReport(Date date1, Date date2);
	public List<CustomerMemos> getCustomerIDsFromMemos(Date date1, Date date2);
	public List<Long> removeListDataFromMemos(List<CustomerMemos> memos);
	public List<Customers> getCustomerNamesFromIDs(List<Long> customerIDs);
}
