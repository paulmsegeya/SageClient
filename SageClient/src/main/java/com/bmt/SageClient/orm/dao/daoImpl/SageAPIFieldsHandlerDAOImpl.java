package com.bmt.SageClient.orm.dao.daoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.bmt.SageClient.GlobalVars;
import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.Name;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;
import com.bmt.SageClient.orm.dao.RequestHeaders;
import com.bmt.SageClient.orm.dao.SageAPIFieldsHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerEmails;
import com.bmt.SageClient.sage200api.entities.CustomerTelephones;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;


@Repository
public class SageAPIFieldsHandlerDAOImpl extends RequestHeaders implements SageAPIFieldsHandlerDAO
{
	
	public void setToken() {
		headers.set("Authorization", "Bearer " +  GlobalVars.accessToken.replace(" ", ""));
	}
	
	

	@Override
	public ServerResponse addUpdateEmail(Email email) {
		if(email.getId() == 0) return addEmail(email);
		return updateEmail(email);
	}	

	@Override
	public ServerResponse addEmail(Email email) {
		setToken();
		ServerResponse serverResponse;
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customers/" + email.getCustomerID() + "/customer_contacts/" + email.getCustomerContactID() + "/customer_emails");
			CustomerEmails emailRequestBody = new CustomerEmails();
			emailRequestBody.setEmail(email.getEmail());
			HttpEntity<CustomerEmails> entity = new HttpEntity<CustomerEmails>(emailRequestBody, headers);					
			ResponseEntity<CustomerEmails> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.POST, entity, new ParameterizedTypeReference<CustomerEmails>(){}) ;
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){ return runClientException(clientEx, "Client error adding email"); }
		catch(HttpServerErrorException  serverEx){ return runServerException(serverEx, "Server error adding email"); }
		catch(Exception e) { return runUnknownException(e); }
		
		return serverResponse;
	}

	
	@Override
	public ServerResponse updateEmail(Email email) 
	{
		setToken();
		ServerResponse serverResponse;
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_emails/" + email.getId());
			CustomerEmails emailRequestBody = new CustomerEmails();
			emailRequestBody.setEmail(email.getEmail());
			HttpEntity<CustomerEmails> entity = new HttpEntity<CustomerEmails>(emailRequestBody, headers);					
			ResponseEntity<CustomerEmails> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.PUT, entity, new ParameterizedTypeReference<CustomerEmails>(){}) ;
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){ return runClientException(clientEx, "Client error updating email"); }
		catch(HttpServerErrorException  serverEx){ return runServerException(serverEx, "Server error updating email"); }
		catch(Exception e) { return runUnknownException(e); }
		
		return serverResponse;
	}

	
	
	
	@Override
	public ServerResponse addUpdateTel(Telephone tel) {
		if(tel.getId() == 0) return addTel(tel);
		return updateTel(tel);
	}
	
	@Override
	public ServerResponse addTel(Telephone tel) 
	{
		setToken();
		ServerResponse serverResponse;
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customers/" + tel.getCustomerID() + "/customer_contacts/" + tel.getCustomerContactID() + "/customer_telephones");
			CustomerTelephones telRequestBody = new CustomerTelephones();
			telRequestBody.setSubscriberNumber(tel.getTelephone());
			HttpEntity<CustomerTelephones> entity = new HttpEntity<CustomerTelephones>(telRequestBody, headers);					
			ResponseEntity<CustomerTelephones> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.POST, entity, new ParameterizedTypeReference<CustomerTelephones>(){}) ;
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){ return runClientException(clientEx, "Client error adding telephone number"); }
		catch(HttpServerErrorException  serverEx){ return runServerException(serverEx, "Server error adding telephone number"); }
		catch(Exception e) { return runUnknownException(e); }
		
		return serverResponse;
	}

	@Override
	public ServerResponse updateTel(Telephone tel) 
	{
		setToken();
		ServerResponse serverResponse;
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_telephones/" + tel.getId());
			CustomerTelephones telRequestBody = new CustomerTelephones();
			telRequestBody.setSubscriberNumber(tel.getTelephone());
			HttpEntity<CustomerTelephones> entity = new HttpEntity<CustomerTelephones>(telRequestBody, headers);					
			ResponseEntity<CustomerTelephones> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.PUT, entity, new ParameterizedTypeReference<CustomerTelephones>(){}) ;
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){ return runClientException(clientEx, "Client error updating telephone number"); }
		catch(HttpServerErrorException  serverEx){ return runServerException(serverEx, "Server error updating telephone number"); }
		catch(Exception e) { return runUnknownException(e); }
		
		return serverResponse;
	}
	
	


	@Override
	public ServerResponse updateName(long customerContactID, Name name) 
	{
		setToken();
		ServerResponse serverResponse;
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			CustomersContacts customerContactsRequestBody = new CustomersContacts();
			customerContactsRequestBody.setFirstName(name.getFirstName());
			customerContactsRequestBody.setMiddleName(name.getMiddleName());
			customerContactsRequestBody.setLastName(name.getLastName());
			HttpEntity<CustomersContacts> entity = new HttpEntity<CustomersContacts>(customerContactsRequestBody, headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_contacts/" + String.valueOf(customerContactID));
	
			ResponseEntity<CustomersContacts> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.PUT,
					entity,
			  new ParameterizedTypeReference<CustomersContacts>(){});
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){ return runClientException(clientEx, "Client error updating customer name"); }
		catch(HttpServerErrorException  serverEx){ return runServerException(serverEx, "Server error updating customer name"); }
		catch(Exception e) { return runUnknownException(e); }
		
		return serverResponse;
	}

	

	@Override 
	public ServerResponse updateSignedDate(long customerID, Date signedDate) {
		setToken();
		ServerResponse serverResponse;
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  		
			Customers customerRequestBody = new Customers();
			customerRequestBody.setAnalysisCode2(dateFormat.format(signedDate));
			HttpEntity<Customers> entity = new HttpEntity<Customers>(customerRequestBody, headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customers/" + String.valueOf(customerID));
	
			ResponseEntity<Customers> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.PUT,
					entity,
			  new ParameterizedTypeReference<Customers>(){});
			
			serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx){ return runClientException(clientEx, "Client error updating signed date"); }
		catch(HttpServerErrorException  serverEx){ return runServerException(serverEx, "Server error updating signed date"); }
		catch(Exception e) { return runUnknownException(e); }
		
		return serverResponse;
		
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
		System.out.println(clientEx.getResponseBodyAsString());
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
