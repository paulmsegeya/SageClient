package com.bmt.SageClient.orm.dao.daoImpl;

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
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.SageAPIFieldsHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerEmails;


@Repository
public class SageAPIFieldsHandlerDAOImpl implements SageAPIFieldsHandlerDAO
{
	HttpHeaders headers;
	
	public SageAPIFieldsHandlerDAOImpl() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + GlobalVars.accessToken);		
		headers.set("ocp-apim-subscription-key", "39cfbba1883b4f71931a6b3c495d3c68"); 
		headers.set("X-Company", GlobalVars.SageAPICompanyID); 
		headers.set("Content-Type", "application/json"); //"application/x-www-form-urlencoded"); 
		headers.set("X-Site", "c3a91133-a250-c54f-e9ac-08d507348a36");
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	} 
	
	
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
