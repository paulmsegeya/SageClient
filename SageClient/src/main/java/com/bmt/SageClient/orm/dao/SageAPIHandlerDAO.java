package com.bmt.SageClient.orm.dao;

import com.bmt.SageClient.api_dataTypes.SageConnectionTest;
import com.bmt.SageClient.api_dataTypes.SageInterfaceConnection;

public interface SageAPIHandlerDAO 
{
	public SageConnectionTest testSageAPIConnection();
	public SageInterfaceConnection testSageInterfaceConnection();

}
