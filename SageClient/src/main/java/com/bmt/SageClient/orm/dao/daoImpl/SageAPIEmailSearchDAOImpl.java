package com.bmt.SageClient.orm.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bmt.SageClient.GlobalVars;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.RequestHeaders;
import com.bmt.SageClient.orm.dao.SageAPIEmailSearchDAO;
import com.bmt.SageClient.orm.dao.SageAPITelSearchDAO;
import com.bmt.SageClient.sage200api.entities.CustomerEmails;
import com.bmt.SageClient.sage200api.entities.CustomerTelephones;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;


@Repository
public class SageAPIEmailSearchDAOImpl extends RequestHeaders implements SageAPIEmailSearchDAO
{

	
	
	public void setToken() {
		headers.set("Authorization", "Bearer " +  GlobalVars.accessToken.replace(" ", ""));
	}


	@Override
	public List<String> seachViaEmail(String emailStr) {
		List<CustomerEmails> emails = getContactIDViaEmail(emailStr);
		List<CustomersContacts> customerContacts = new ArrayList<>();
		
		for(CustomerEmails email : emails) {
			for(CustomersContacts customerContact :  
				requestCustomerContacts(Long.valueOf(email.getCustomerContactID())) ) {
				customerContacts.add(customerContact);
			}
		}
		

		List<String> customerNames = new ArrayList<>();
		for(Customers customer : getCustomers(customerContacts)) {
			customerNames.add(customer.getName());
		}
		return customerNames;
	}
	

	@Override
	public List<Customers> getCustomers(List<CustomersContacts> customerContacts) 
	{
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			
			String idFilter = "";
			for(int x = 0; x < customerContacts.size(); x++) {
				idFilter += "id eq " + customerContacts.get(x).getCustomerID();
				if(x < customerContacts.size() -1 ) idFilter += "or ";
			}
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customers")
			        .queryParam("$filter",  idFilter)
			        .queryParam("$select", "name");
	
			ResponseEntity<List<Customers>> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.GET,
					entity,
			  new ParameterizedTypeReference<List<Customers>>(){});
			List<Customers> customers = response.getBody();
			return customers;
		}
		catch(HttpClientErrorException clientEx){
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx){
			System.out.println(serverEx);
		}
		
		return new ArrayList<Customers>();
	}



	

	
	@Override
	public List<CustomerEmails> getContactIDViaEmail(String email) 
	{
		setToken();
		ServerResponse serverResponse;
		List<CustomerEmails> emails = new ArrayList<>(); 
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_emails")
			        .queryParam("$filter", "email  eq '" + email + "'")
			        .queryParam("$select", "customer_contact_id");
			ResponseEntity<List<CustomerEmails>> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.GET,
					entity,
			  new ParameterizedTypeReference<List<CustomerEmails>>(){});
			emails = response.getBody();
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){  runClientException(clientEx, "Client error searching email addresses"); }
		catch(HttpServerErrorException  serverEx){  runServerException(serverEx, "Server error searching email addresses"); }
		catch(Exception e) {  runUnknownException(e); }
		
		return emails;
	}

	
	
	public List<CustomersContacts> requestCustomerContacts(Long id)
	{
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_contacts")
			        .queryParam("$filter", "id eq " + id)
			        .queryParam("$select", "customer_id");
			
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
	
	
	
	
	private ServerResponse runServerException(HttpServerErrorException  serverEx, String msg) {
		System.out.println(serverEx.getMessage());
		System.out.println(serverEx.getStackTrace());
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(false);
		serverResponse.setHttpStatus( serverEx.getStatusText() );
		serverResponse.setErrorSource("Server");
		serverResponse.setMessage(msg);
		return serverResponse;
	}

	private ServerResponse runClientException(HttpClientErrorException clientEx, String msg) {
		System.out.println(clientEx.getMessage());
		System.out.println(clientEx.getStackTrace());
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(false);
		serverResponse.setHttpStatus( clientEx.getStatusText() );
		serverResponse.setErrorSource("Client");
		serverResponse.setMessage(msg);
		return serverResponse;
	}
	
	private ServerResponse runUnknownException(Exception e) {
		System.out.println(e.getMessage());
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(false);
		serverResponse.setHttpStatus( "Unknown" );
		serverResponse.setErrorSource("Unknown");
		serverResponse.setMessage("Unknown");
		return serverResponse;
	}










}
