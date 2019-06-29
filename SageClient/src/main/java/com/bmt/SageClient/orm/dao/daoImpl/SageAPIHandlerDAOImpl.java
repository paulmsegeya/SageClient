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
	
	public List<CustomerViews> requestCustomerViews(String endPoint)
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
				"https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_views?$filter=reference eq '00001001'",
		  HttpMethod.GET,
		  entity,
		  new ParameterizedTypeReference<List<CustomerViews>>(){});
		List<CustomerViews> customerViews = response.getBody();
		return customerViews;
		//if(customerViews.size() > 0) return customerViews.get(0).getName();
		// return"no resutls";
	}

}
