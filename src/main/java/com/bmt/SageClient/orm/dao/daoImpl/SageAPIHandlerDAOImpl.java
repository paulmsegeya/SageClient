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
