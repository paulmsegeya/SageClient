package com.bmt.SageClient.orm.dao;

import org.springframework.http.HttpStatus;
import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.CustomerListData.ListData;

public interface SageAPIMemoHandlerDAO 
{
	public HttpStatus addUpdateInterviews(CustomerListData interviews);
	public HttpStatus updateInterviews(ListData interviews );
	public HttpStatus addInterviews(Long customerID, ListData interviews );

}
