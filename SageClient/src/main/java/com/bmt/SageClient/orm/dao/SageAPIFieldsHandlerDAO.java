package com.bmt.SageClient.orm.dao;

import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;

public interface SageAPIFieldsHandlerDAO {
	
	public ServerResponse addUpdateEmail(Email email);
	public ServerResponse addEmail(Email email);
	public ServerResponse updateEmail(Email email);
	
	public ServerResponse addUpdateTel(Telephone tel);
	public ServerResponse addTel(Telephone tel);
	public ServerResponse updateTel(Telephone tel);
	
	//public ServerResponse addUpdateName(long customerID, String name);
	//public ServerResponse addName(long customerID, String name);
	public ServerResponse updateName(long customerID, String name);

}
