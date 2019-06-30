package com.bmt.SageClient.orm.dao.daoImpl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerViews;;


@Repository
public class SageAPIHandlerDAOImpl implements SageAPIHandlerDAO 
{
	
	public List<CustomerViews> requestCustomerViews(String customerName)
	{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.set("Authorization", "Bearer ");
		headers.set("Authorization", "Bearer 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");
		headers.set("ocp-apim-subscription-key", "39cfbba1883b4f71931a6b3c495d3c68"); 
		headers.set("X-Company", "1"); 
		headers.set("Content-Type", "application/x-www-form-urlencoded"); 
		headers.set("X-Site", "c3a91133-a250-c54f-e9ac-08d507348a36"); 
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<List<CustomerViews>> response = restTemplate.exchange(
		  //"https://api.columbus.sage.com/uk/sage200extra/accounts/v1/" + endPoint,
				"https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_views?$filter=name eq '" + customerName + "'",
		  HttpMethod.GET,
		  entity,
		  new ParameterizedTypeReference<List<CustomerViews>>(){});
		List<CustomerViews> customerViews = response.getBody();
		return customerViews;
	}

}
