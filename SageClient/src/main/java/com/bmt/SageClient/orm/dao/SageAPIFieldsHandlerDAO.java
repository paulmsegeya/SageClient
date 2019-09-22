package com.bmt.SageClient.orm.dao;

import java.util.Date;

import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.Name;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;

public interface SageAPIFieldsHandlerDAO {
	
	public ServerResponse addUpdateEmail(Email email);
	public ServerResponse addEmail(Email email);
	public ServerResponse updateEmail(Email email);
	
	public ServerResponse addUpdateTel(Telephone tel);
	public ServerResponse addTel(Telephone tel);
	public ServerResponse updateTel(Telephone tel);

	public ServerResponse updateName(long customerContactID, Name name);
	public ServerResponse updateSignedDate(long customerID, Date signedDate);

}

