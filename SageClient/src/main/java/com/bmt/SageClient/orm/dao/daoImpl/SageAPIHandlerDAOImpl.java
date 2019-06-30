package com.bmt.SageClient.orm.dao.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;;


@Repository
public class SageAPIHandlerDAOImpl implements SageAPIHandlerDAO 
{
	HttpHeaders headers;
	
	public SageAPIHandlerDAOImpl() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.set("Authorization", "Bearer");
		headers.set("Authorization", "Bearer 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");
		headers.set("ocp-apim-subscription-key", "39cfbba1883b4f71931a6b3c495d3c68"); 
		headers.set("X-Company", "1"); 
		headers.set("Content-Type", "application/x-www-form-urlencoded"); 
		headers.set("X-Site", "c3a91133-a250-c54f-e9ac-08d507348a36");		
	} 
	
	
	public CustomerInfo getCustomerInfo(String name)
	{
		CustomerInfo customerInfo = new CustomerInfo();
		List<Customers> customers = requestCustomers(name);
		if(customers.size() > 0) 
		{
			Customers customer = customers.get(0);
			List<CustomerViews> customerViews = requestCustomerViews(customer.getId());
			List<CustomersContacts> customersContacts = requestCustomerContacts(customer.getId());
			List<Transactions> transactions = requestTransactions(customer.getId());
			List<CustomerMemos> memos = requestMemos(customer.getId());
			
			
			if(customerViews.size() > 0)
			{
				CustomerViews customerView = customerViews.get(0);
				customerInfo.setAccountRef(customerView.getReference());
				customerInfo.setAccountName(customerView.getName());
				customerInfo.setShortName(customerView.getShortName());
				customerInfo.setSignedDate(customerView.getAnalysisCode2());
				customerInfo.setType(customerView.getAnalysisCode1());
				customerInfo.setTel(customerView.getTelephoneSubscriberNumber());
				customerInfo.setSeenContact(customerView.getAnalysisCode3());
			}			
			
			if(customersContacts.size() > 0){
				customerInfo.setCustomerName(customersContacts.get(0).getName());
				customerInfo.setEmail(customersContacts.get(0).getDefaultEmail());
				if(customersContacts.size() >= 2) customerInfo.setEmail2(customersContacts.get(1).getDefaultEmail());				
			}
			
			if(transactions.size() > 0){
				Transactions transaction = transactions.get(0);
				customerInfo.setDateOfShoot(transaction.getTransactionDate());
				customerInfo.setTransPackage(transaction.getReference());
				customerInfo.setTotal(transaction.getDocumentGrossValue());
				customerInfo.setInvoiceBalance(transaction.getDocumentOutstandingValue());
			}
			
			String notes = "";
			for(CustomerMemos memo : memos) {
				notes = notes.concat(memo.getNote());
			}	
			customerInfo.setNotes(notes);			
		}		
		
		return customerInfo;
		
	}
	
	
	public List<Customers> requestCustomers(String name)
	{
		RestTemplate restTemplate = new RestTemplate();		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customers")
		        .queryParam("$filter", "name eq '" + name + "'")
		        .queryParam("$select", "id,reference,name,short_name");

		System.out.println(builder.toUriString().replaceAll("%20", " "));
		ResponseEntity<List<Customers>> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.GET,
				entity,
		  new ParameterizedTypeReference<List<Customers>>(){});
		List<Customers> customers = response.getBody();
		return customers;

	}
	
	
	public List<CustomerViews> requestCustomerViews(Long id)
	{
		RestTemplate restTemplate = new RestTemplate();		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_views")
		        .queryParam("$filter", "id eq " + id)
		        .queryParam("$select", "id,reference,name,short_name,analysis_code_1,analysis_code_2,analysis_code_3,telephone_subscriber_number");
		
		ResponseEntity<List<CustomerViews>> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.GET,
				entity,
		  new ParameterizedTypeReference<List<CustomerViews>>(){});
		List<CustomerViews> customerViews = response.getBody();
		return customerViews;
	}
	
	
	public List<CustomersContacts> requestCustomerContacts(Long id)
	{
		RestTemplate restTemplate = new RestTemplate();		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_contacts")
		        .queryParam("$filter", "customer_id eq " + id)
		        .queryParam("$select", "id,name,default_email");
		
		ResponseEntity<List<CustomersContacts>> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.GET,
				entity,
		  new ParameterizedTypeReference<List<CustomersContacts>>(){});
		List<CustomersContacts> customerContacts = response.getBody();
		return customerContacts;
	}
	
	
	public List<Transactions> requestTransactions(Long id)
	{
		RestTemplate restTemplate = new RestTemplate();		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/sales_posted_transactions")
		        .queryParam("$filter", "customer_id eq " + id)
		        .queryParam("$select", "id,customer_id,transaction_date,reference,document_gross_value,document_outstanding_value")
		        .queryParam("$orderby", "urn asc")
		        .queryParam("$top", 1);
		
		ResponseEntity<List<Transactions>> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.GET,
				entity,
		  new ParameterizedTypeReference<List<Transactions>>(){});
		List<Transactions> transactions = response.getBody();
		return transactions;
	}
	
	
	public List<CustomerMemos> requestMemos(Long id)
	{
		RestTemplate restTemplate = new RestTemplate();		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_memos")
		        .queryParam("$filter", "customer_id eq " + id)
		        .queryParam("$select", "id,customer_id,note");
		
		ResponseEntity<List<CustomerMemos>> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.GET,
				entity,
		  new ParameterizedTypeReference<List<CustomerMemos>>(){});
		List<CustomerMemos> memos = response.getBody();
		return memos;
	}

}
