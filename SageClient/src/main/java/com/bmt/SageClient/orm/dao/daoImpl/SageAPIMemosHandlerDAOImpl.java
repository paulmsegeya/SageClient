package com.bmt.SageClient.orm.dao.daoImpl;

import com.bmt.SageClient.GlobalVars;

import java.util.ArrayList;
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

import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.CustomerListData.ListData;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.SageAPIMemoHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerMemosPOST;
import com.bmt.SageClient.sage200api.entities.CustomerMemosPUT;
import com.google.gson.Gson;


@Repository
public class SageAPIMemosHandlerDAOImpl implements SageAPIMemoHandlerDAO 
{
	HttpHeaders headers;
	
	public SageAPIMemosHandlerDAOImpl() {
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
	
	@Override
	public List<ServerResponse> addUpdateListData(CustomerListData listData)
	{
		List<ServerResponse> serverResponses = new ArrayList<>();
		
		if(listData.getInterviews().isShouldBeUpdated()) {
			if(listData.getInterviews().getMemoID() != null) serverResponses.add( updateListData(listData.getInterviews()) );
			else serverResponses.add( addListData(listData.getCustomerID(), listData.getInterviews()) );
		}
		if(listData.getChosenAgencies().isShouldBeUpdated()) {
			if(listData.getChosenAgencies().getMemoID() != null) serverResponses.add( updateListData(listData.getChosenAgencies()) );
			else serverResponses.add( addListData(listData.getCustomerID(), listData.getChosenAgencies()) );
		}
		if(listData.getCasting().isShouldBeUpdated()) {
			if(listData.getCasting().getMemoID() != null) serverResponses.add( updateListData(listData.getCasting()) );
			else serverResponses.add( addListData(listData.getCustomerID(), listData.getCasting()) );
		}
		if(listData.getOffers().isShouldBeUpdated()) {
			if(listData.getOffers().getMemoID() != null) serverResponses.add( updateListData(listData.getOffers()) );
			else serverResponses.add( addListData(listData.getCustomerID(), listData.getOffers()) );
		}
		if(listData.getSelfTapes().isShouldBeUpdated()) {
			if(listData.getSelfTapes().getMemoID() != null) serverResponses.add( updateListData(listData.getSelfTapes()) );
			else serverResponses.add( addListData(listData.getCustomerID(), listData.getSelfTapes()) );
		}
		return serverResponses;
	}

	
	@Override
	public ServerResponse addUpdateListData(Long customerID, ListData listData) {
		if(listData.getMemoID() != null) return updateListData(listData);
		else return addListData(customerID, listData);
	}
	
	

	@Override
	public ServerResponse updateListData(ListData listData )
	{
		setToken();
		try
		{
			String interviewArrStr =  listData.getName() + ( new Gson().toJson(listData.getData()) );
			
			RestTemplate restTemplate = new RestTemplate();		
			CustomerMemosPUT memoRequestBody = new CustomerMemosPUT();
			memoRequestBody.setNote(interviewArrStr);
			HttpEntity<CustomerMemosPUT> entity = new HttpEntity<CustomerMemosPUT>(memoRequestBody, headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_memos/" + listData.getMemoID());
	
			ResponseEntity<CustomerMemos> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.PUT,
					entity,
			  new ParameterizedTypeReference<CustomerMemos>(){});
		}
		catch(HttpClientErrorException clientEx)
		{
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(false);
			serverResponse.setHttpStatus( clientEx.getStatusText() );
			serverResponse.setErrorSource("Client");
			serverResponse.setMessage("Client error updating " + listData.getName());
		}
		catch(HttpServerErrorException  serverEx)
		{
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(false);
			serverResponse.setHttpStatus( serverEx.getStatusText() );
			serverResponse.setErrorSource("Server");
			serverResponse.setMessage("Server error updating " + listData.getName());
		}
		
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(false);
		serverResponse.setHttpStatus( "Unknown" );
		serverResponse.setErrorSource("Unknown");
		serverResponse.setMessage("Unknown");
		
		return serverResponse;
	}
	
	
	
	@Override
	public ServerResponse addListData(Long customerID, ListData listData )
	{
		setToken();
		try
		{
			String interviewArrStr =  listData.getName() + ( new Gson().toJson(listData.getData()) );
			
			RestTemplate restTemplate = new RestTemplate();		
			CustomerMemosPOST memoRequestBody = new CustomerMemosPOST();
			memoRequestBody.setCustomerId(customerID);
			memoRequestBody.setNote(interviewArrStr);
			HttpEntity<CustomerMemosPOST> entity = new HttpEntity<CustomerMemosPOST>(memoRequestBody, headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_memos");
	
			ResponseEntity<CustomerMemos> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.POST,
					entity,
			  new ParameterizedTypeReference<CustomerMemos>(){});
			
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(true);
			serverResponse.setHttpStatus( String.valueOf(response.getStatusCodeValue()) );
		}
		catch(HttpClientErrorException clientEx)
		{
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(false);
			serverResponse.setHttpStatus( clientEx.getStatusText() );
			serverResponse.setErrorSource("Client");
			serverResponse.setMessage("Client error adding " + listData.getName());
		}
		catch(HttpServerErrorException  serverEx)
		{
			ServerResponse serverResponse = new ServerResponse();
			serverResponse.setSuccess(false);
			serverResponse.setHttpStatus( serverEx.getStatusText() );
			serverResponse.setErrorSource("Server");
			serverResponse.setMessage("Server error adding " + listData.getName());
		}
		
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setSuccess(false);
		serverResponse.setHttpStatus( "Unknown" );
		serverResponse.setErrorSource("Unknown");
		serverResponse.setMessage("Unknown");
		
		return serverResponse;
	}



	

	
	

}
