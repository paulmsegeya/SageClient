package com.bmt.SageClient.orm.dao.daoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.orm.dao.RequestHeaders;
import com.bmt.SageClient.orm.dao.SageAPICustomerHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIReportDAO;
import com.bmt.SageClient.sage200api.CustomerMemoListData.MemoListDataTypes;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.Customers;

@Repository
public class SageAPIReportDAOImpl extends RequestHeaders implements SageAPIReportDAO 
{
	
	@Autowired
	SageAPICustomerHandlerDAO customerHandlerDAO;
	
	public void setToken() {
		headers.set("Authorization", "Bearer " +  GlobalVars.accessToken.replace(" ", ""));
	}
	

	@Override
	public List<CustomerInfo> getReport(Date date1, Date date2) 
	{
		List<CustomerMemos> memos = getCustomerIDsFromMemos(date1, date2); 
		List<Long> customerIDs = removeListDataFromMemos(memos); 
		List<Customers> customerNames = getCustomerNamesFromIDs(customerIDs);
		
		List<CustomerInfo> customerInfo = new ArrayList<>();
		for(Customers customer: customerNames) {
			customerInfo.add( customerHandlerDAO.getCustomerInfo(customer.getName()) );
		}
		return customerInfo;
	}
	
	
	@Override
	public List<CustomerMemos> getCustomerIDsFromMemos(Date date1, Date date2) 
	{
		setToken();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  		
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.columbus.sage.com/uk/sage200extra/accounts/v1/customer_memos")
			        .queryParam("$filter", "date_time_updated gt " + dateFormat.format(date1) + " and date_time_updated  le " + dateFormat.format(date2))
			        .queryParam("$select", "customer_id, note");
			
			ResponseEntity<List<CustomerMemos>> response = restTemplate.exchange(
					builder.toUriString().replaceAll("%20", " "),
					HttpMethod.GET,
					entity,
			  new ParameterizedTypeReference<List<CustomerMemos>>(){});
			List<CustomerMemos> memos = response.getBody();
			return memos;
		}
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx);
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
	
		return new ArrayList<CustomerMemos>();		
	}



	@Override
	public List<Long> removeListDataFromMemos(List<CustomerMemos> memos) 
	{
		List<Long> customerIDs = new ArrayList<>();
		for(CustomerMemos memo: memos) 
		{
			if( !memo.getNote().startsWith(MemoListDataTypes.INTERVIEWS.toString()) &&  !memo.getNote().startsWith(MemoListDataTypes.CHOSEN_AGENCIES.toString()) 
					&& !memo.getNote().startsWith(MemoListDataTypes.OFFERS.toString()) && !memo.getNote().startsWith(MemoListDataTypes.CASTING_DIRECTORIES.toString())  && !memo.getNote().startsWith(MemoListDataTypes.SELF_TAPES.toString())) {
				customerIDs.add(memo.getCustomerId());
			}
		}
		return customerIDs;
	}



	@Override
	public List<Customers> getCustomerNamesFromIDs(List<Long> customerIDs) 
	{
		setToken();
		String idFilter = "";
		for(int x = 0; x < customerIDs.size(); x++) {
			idFilter += "id eq " + customerIDs.get(x);
			if(x < customerIDs.size() -1 ) idFilter += "or ";
		}
		
		try
		{
			RestTemplate restTemplate = new RestTemplate();		
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);		
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
		catch(HttpClientErrorException clientEx)
		{
			System.out.println(clientEx.getMessage());
		}
		catch(HttpServerErrorException  serverEx)
		{
			System.out.println(serverEx);
		}
		
		return new ArrayList<Customers>();

	}



	
	
	

}
