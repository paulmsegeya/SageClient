package com.bmt.SageClient.orm.dao.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	public List<String> requestCustomerNames(String namePart)
	{
		RestTemplate restTemplate = new RestTemplate();		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customers")
		        .queryParam("$filter", "contains(name, '" + namePart + "')")
		        .queryParam("$select", "name")
		        .queryParam("$top", 10);
		
		ResponseEntity<List<Customers>> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.GET,
				entity,
		  new ParameterizedTypeReference<List<Customers>>(){});
		List<Customers> customers = response.getBody();
		
		String[] customerNames = new String[customers.size()];
		for(int x = 0; x < customers.size(); x++) {
			customerNames[x] = customers.get(x).getName();
		}
		Arrays.sort(customerNames);
		
		return new ArrayList<>(Arrays.asList(customerNames));
	}
	
	
	

}
