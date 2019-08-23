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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.Note;
import com.bmt.SageClient.api_dataTypes.Telephone;
import com.bmt.SageClient.orm.dao.RequestHeaders;
import com.bmt.SageClient.orm.dao.SageAPICustomerHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.sage200api.CustomerMemoListData.MemoListDataTypes;
import com.bmt.SageClient.sage200api.entities.CustomerEmails;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerTelephones;
import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;;


@Repository
public class SageAPICustomerHandlerDAOImpl extends RequestHeaders implements SageAPICustomerHandlerDAO 
{
	
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
				customerInfo.setCustomerID(customerView.getId());
				customerInfo.setAccountRef(customerView.getReference());
				customerInfo.setAccountName(customerView.getName());
				customerInfo.setShortName(customerView.getShortName());
				customerInfo.setSignedDate(customerView.getAnalysisCode2());
				customerInfo.setType(customerView.getAnalysisCode1());
				customerInfo.setSeenContact(customerView.getAnalysisCode3());
			}

			
			if(customersContacts.size() > 0)
			{
				//Emails
				customerInfo.setCustomerName(customersContacts.get(0).getName());						
				
				List<CustomerEmails> customerEmails = requestEmails(customersContacts.get(0).getId());
				Email email1 = new Email();
				Email email2 = new Email();
				
				email1.setCustomerContactID(customersContacts.get(0).getId() );
				email1.setCustomerID(customer.getId());
				email2.setCustomerContactID(customersContacts.get(0).getId() );
				email2.setCustomerID(customer.getId());
				
				if(customerEmails.size() > 0){					
					email1.setId(customerEmails.get(0).getId() );
					email1.setEmail(customerEmails.get(0).getEmail() );
				}
				if(customerEmails.size() > 1){					
					email2.setId(customerEmails.get(1).getId() );
					email2.setEmail(customerEmails.get(1).getEmail() );
				}
				customerInfo.setEmail(email1);
				customerInfo.setEmail2(email2);		
				
				//Telephones				
				List<CustomerTelephones> customerTels = requestTel(customersContacts.get(0).getId());
				Telephone tel1 = new Telephone();
				Telephone tel2 = new Telephone();
				
				tel1.setCustomerContactID(customersContacts.get(0).getId() );
				tel1.setCustomerID(customer.getId());
				tel2.setCustomerContactID(customersContacts.get(0).getId() );
				tel2.setCustomerID(customer.getId());
				
				if(customerTels.size() > 0){					
					tel1.setId(customerTels.get(0).getId() );
					tel1.setTelephone(customerTels.get(0).getSubscriberNumber() );
				}
				if(customerTels.size() > 1){					
					
					tel2.setId(customerTels.get(1).getId() );
					tel2.setTelephone(customerTels.get(1).getSubscriberNumber() );
				}
				customerInfo.setTel(tel1);				
				customerInfo.setTel2(tel2);		
			}			
			
			
			if(transactions.size() > 0){
				Transactions transaction = transactions.get(0);
				customerInfo.setDateOfShoot(transaction.getTransactionDate());
				customerInfo.setTransPackage(transaction.getReference());
				customerInfo.setTotal(transaction.getDocumentGrossValue());
				customerInfo.setInvoiceBalance(transaction.getDocumentOutstandingValue());
			}
			
			setListData(memos, customerInfo);
		}		
		
		return customerInfo;
		
	}
	
	
	private void setListData(List<CustomerMemos> memos, CustomerInfo customerInfo)
	{
		CustomerListData listData = new CustomerListData();
		listData.setCustomerID(customerInfo.getCustomerID());
		for(CustomerMemos memo : memos) 
		{

			String note = memo.getNote();
			if( (note.length() >= 2) &&   (note.indexOf('[') >=  0)     &&   ( note.indexOf(']') == note.length() - 1)  ) //contains array for list data
			{
				String listDataType = note.substring(0, note.indexOf('[')).trim().toUpperCase();
				String arrayStr = note.substring(note.indexOf('['), note.length());
				
				try 
				{
					TypeToken<List<String>> token = new TypeToken<List<String>>() {};
					List<String> listArrayData = new Gson().fromJson(arrayStr, token.getType());
					
					try{
						MemoListDataTypes selectedType = MemoListDataTypes.valueOf(listDataType);
						switch (selectedType) 
						{
							case INTERVIEWS:
								listData.getInterviews().setMemoID(memo.getId());
								listData.getInterviews().setData(listArrayData);
								listData.getInterviews().setName(selectedType);
								break;
							case CHOSEN_AGENCIES:
								listData.getChosenAgencies().setMemoID(memo.getId());
								listData.getChosenAgencies().setData(listArrayData);
								listData.getChosenAgencies().setName(selectedType);
								break;
							case OFFERS:
								listData.getOffers().setMemoID(memo.getId());
								listData.getOffers().setData(listArrayData);
								listData.getOffers().setName(selectedType);
								break;
							case CASTING_DIRECTORIES:
								listData.getCasting().setMemoID(memo.getId());
								listData.getCasting().setData(listArrayData);
								listData.getCasting().setName(selectedType);
								break;
							case SELF_TAPES:
								listData.getSelfTapes().setMemoID(memo.getId());
								listData.getSelfTapes().setData(listArrayData);
								listData.getSelfTapes().setName(selectedType);
								break;
						}
					}
					catch(IllegalArgumentException iaEx) {
						System.out.println("Error identifying sage data: " + listDataType);
					}
				}
				catch(JsonParseException jpEx) {
					System.out.println("Error reading " + listDataType + " data");
				}
				
			}
			else customerInfo.addMemo(new Note(memo.getId(), memo.getCustomerId(), memo.getNote())    );	//regular memo
		}			
		customerInfo.setListData(listData);
	}
	
	
	public List<Customers> requestCustomers(String name)
	{
		try
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<Customers>();

	}
	
	
	public List<CustomerViews> requestCustomerViews(Long id)
	{
		try
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<CustomerViews>();
	}
	
	
	public List<CustomersContacts> requestCustomerContacts(Long id)
	{
		try
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<CustomersContacts>();
	}
	
	
	public List<Transactions> requestTransactions(Long id)
	{
		try
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<Transactions>();
		
	}
	
	
	public List<CustomerMemos> requestMemos(Long id)
	{
		try
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<CustomerMemos>();
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
		
		return new ArrayList<String>();
	}
	
	
	public List<CustomerEmails> requestEmails(long customerContactID)
	{
		setToken();
		try 
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_emails")
			        .queryParam("$filter", "customer_contact_id eq " + customerContactID
			        		);
			
			ResponseEntity<List<CustomerEmails>> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.GET,
					entity,
			  new ParameterizedTypeReference<List<CustomerEmails>>(){});
			List<CustomerEmails> emails = response.getBody();
			return emails;
		}
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<CustomerEmails>();
	}
	
	
	public List<CustomerTelephones> requestTel(long customerContactID)
	{
		setToken();
		try 
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_telephones")
			        .queryParam("$filter", "customer_contact_id eq " + customerContactID
			        		);
			
			ResponseEntity<List<CustomerTelephones>> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.GET,
					entity,
			  new ParameterizedTypeReference<List<CustomerTelephones>>(){});
			List<CustomerTelephones> telNums = response.getBody();
			return telNums;
		}
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<CustomerTelephones>();
	}
	
	
	

}
