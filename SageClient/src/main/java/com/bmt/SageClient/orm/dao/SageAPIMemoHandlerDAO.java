package com.bmt.SageClient.orm.dao;

import java.util.List;

import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.CustomerListData.ListData;
import com.bmt.SageClient.api_dataTypes.ServerResponse;

public interface SageAPIMemoHandlerDAO 
{
	public List<ServerResponse> addUpdateListData(CustomerListData listData);
	public ServerResponse addUpdateListData(Long customerID, ListData listData);
	public ServerResponse updateListData(ListData listData );
	public ServerResponse addListData(Long customerID, ListData listData );

}
