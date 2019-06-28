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
	
	public void requestCustomerViews(String endPoint)
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
		
		if(customerViews.size() > 0) System.out.println(customerViews.get(0).getName());
		else System.out.println("no resutls");
	}

}
