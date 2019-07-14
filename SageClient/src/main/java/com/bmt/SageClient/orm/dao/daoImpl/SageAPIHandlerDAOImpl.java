package com.bmt.SageClient.orm.dao.daoImpl;

import com.bmt.SageClient.GlobalVars;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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
		headers.set("Authorization", "Bearer " + GlobalVars.accessToken);		
		headers.set("ocp-apim-subscription-key", "39cfbba1883b4f71931a6b3c495d3c68"); 
		headers.set("X-Company", "1"); 
		headers.set("Content-Type", "application/x-www-form-urlencoded"); 
		headers.set("X-Site", "c3a91133-a250-c54f-e9ac-08d507348a36");		
	} 
	
	
	public void setToken() {
		headers.set("Authorization", "Bearer " +  GlobalVars.accessToken.replace(" ", ""));
	}
	
	public CustomerInfo getCustomerInfo(String name)
	{
		setToken();
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
		setToken();
		try 
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return null;
	}
	
	
	

}
