package com.bmt.SageClient.orm.dao.daoImpl;

import com.bmt.SageClient.GlobalVars;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import com.bmt.SageClient.api_dataTypes.SageConnectionTest;
import com.bmt.SageClient.api_dataTypes.SageInterfaceConnection;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.sage200api.CustomerMemoListData.MemoListDataTypes;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;;


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
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	} 
	
	
	public void setToken() {
		headers.set("Authorization", "Bearer " +  GlobalVars.accessToken.replace(" ", ""));
	}
	
	public SageConnectionTest testSageAPIConnection() 
	{		
		setToken();
		try 
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/current_user");
			
			ResponseEntity<SageConnectionTest> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.GET,
					entity,
			  new ParameterizedTypeReference<SageConnectionTest>(){});
			
			SageConnectionTest connTest = response.getBody();
			connTest.setConnectedToSage(true);
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
			connTest.setServerResponse(serverResponse);
			return connTest;
		}
		catch(HttpClientErrorException clientEx)
		{
			SageConnectionTest connTest = new SageConnectionTest();
			connTest.setConnectedToSage(false);
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(false);
			serverResponse.setHttpStatus( clientEx.getStatusText() );
			serverResponse.setErrorSource("Client");
			serverResponse.setMessage("Client error connecting to sage, attempted to retireve current user. ");
		}
		catch(HttpServerErrorException  serverEx)
		{
			SageConnectionTest connTest = new SageConnectionTest();
			connTest.setConnectedToSage(false);
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(false);
			serverResponse.setHttpStatus( serverEx.getStatusText() );
			serverResponse.setErrorSource("Server");
			serverResponse.setMessage("Server error connecting to sage, attempted to retireve current user. ");
		}
		
		SageConnectionTest connTest = new SageConnectionTest();
		connTest.setConnectedToSage(false);
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(false);
		serverResponse.setHttpStatus( "Unknown" );
		serverResponse.setErrorSource("Unknown");
		serverResponse.setMessage("Unknown");
		connTest.setServerResponse(serverResponse);
		return connTest;
	}


	@Override
	public SageInterfaceConnection testSageInterfaceConnection() 
	{
		SageInterfaceConnection interfaceConn = new SageInterfaceConnection();
		interfaceConn.setSageInterfaceConnected(true);
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			interfaceConn.setHostName(inetAddress.getHostName());
			interfaceConn.setIpAddress(inetAddress.getHostAddress());
			
		} catch (UnknownHostException e) {
			interfaceConn.setHostName("Unknown");
			interfaceConn.setIpAddress("Unknown");
		}
		return interfaceConn;
	}
	
	
	

}
