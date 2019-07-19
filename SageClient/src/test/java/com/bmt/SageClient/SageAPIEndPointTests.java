package com.bmt.SageClient;

import java.util.List;

/*import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.orm.dao.SageAPICustomerHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SageAPIEndPointTests {

	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	SageAPICustomerHandlerDAO sageAPICustomerHandler;
	
	
	@Test
	public void contextLoads() {
		
		
	} //getCustomerInfo(String name)
	
	
	@Test
	public void testGetCustomerInfo()
	{
		CustomerInfo customerInfo = sageAPICustomerHandler.getCustomerInfo("donnell abankwa");
		System.out.println( customerInfo.getAccountRef() );
	}

	
	@Test
	public void testRequestCustomers()
	{
		List<Customers> customers = sageAPICustomerHandler.requestCustomers("donnell abankwa");
		System.out.println( customers.get(0).getName() + ", " + customers.get(0).getReference() );
	}
	
	@Test
	public void testRequestCustomerViews()
	{		
		List<CustomerViews> customersViews = sageAPICustomerHandler.requestCustomerViews((long)85680);
		System.out.println( customersViews.get(0).getName() + ", " + customersViews.get(0).getReference() + ", " + customersViews.get(0).getAnalysisCode1() );
	}
	 
	 
	@Test
	public void testRequestCustomerContacts()
	{		
		List<CustomersContacts> customersContacts = sageAPICustomerHandler.requestCustomerContacts((long)85680);
		for(CustomersContacts contact : customersContacts){
			System.out.println( contact.getName() + ", " + contact.getDefaultEmail() + ", " + contact.getDefaultTelephone() );
		}		
	}
	
	
	@Test
	public void testRequestTransactions()
	{		
		List<Transactions> transactions = sageAPICustomerHandler.requestTransactions((long)85680);
		for(Transactions transaction : transactions){
			System.out.println(transaction.getReference() + ", " + transaction.getTransactionDate() + ", " + transaction.getDocumentGrossValue() );
		}		
	}
	
	
	@Test
	public void testRequestMemos()
	{		
		List<CustomerMemos> memos = sageAPICustomerHandler.requestMemos((long)85680);
		for(CustomerMemos memo : memos){
			System.out.println(memo.getId() + ", " +memo.getCustomerId() + ", " + memo.getNote() );
		}		
	}//public List<String> requestCustomerNames(String namePart)
	
	
	@Test
	public void testRequestCustomerNames()
	{		
		List<String> customerNames = sageAPICustomerHandler.requestCustomerNames("do");
		for(String name : customerNames){
			System.out.println(name);
		}		
	}
	
}



