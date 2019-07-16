package com.bmt.SageClient.orm.dao.daoImpl;

import com.bmt.SageClient.GlobalVars;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.CustomerListData.ListData;
import com.bmt.SageClient.orm.dao.SageAPIMemoHandlerDAO;
import com.bmt.SageClient.sage200api.CustomerMemoListData.MemoListDataTypes;
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
	} 
	
	
	public void setToken() {
		headers.set("Authorization", "Bearer " +  GlobalVars.accessToken.replace(" ", ""));
	}
	
	@Override
	public HttpStatus addUpdateInterviews(CustomerListData listData){
		if(listData.getInterviews().isShouldBeUpdated()) return updateInterviews(listData.getInterviews());
		else return addInterviews(listData.getCustomerID(), listData.getInterviews());
	}


	@Override
	public HttpStatus updateInterviews(ListData interviews )
	{
		setToken();
		String interviewArrStr =  MemoListDataTypes.INTERVIEWS.name() + ( new Gson().toJson(interviews.getData()) );
		
		RestTemplate restTemplate = new RestTemplate();		
		CustomerMemosPUT memoRequestBody = new CustomerMemosPUT();
		memoRequestBody.setNote(interviewArrStr);
		HttpEntity<CustomerMemosPUT> entity = new HttpEntity<CustomerMemosPUT>(memoRequestBody, headers);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_memos/" + interviews.getMemoID());

		ResponseEntity<CustomerMemos> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%20", " "),
				HttpMethod.PUT,
				entity,
		  new ParameterizedTypeReference<CustomerMemos>(){});
		
		return response.getStatusCode();
	}
	
	
	
	@Override
	public HttpStatus addInterviews(Long customerID, ListData interviews )
	{
		setToken();
		String interviewArrStr =  MemoListDataTypes.INTERVIEWS.name() + ( new Gson().toJson(interviews) );
		
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
		
		return response.getStatusCode();
	}
	

	
	

}
