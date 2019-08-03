package com.bmt.SageClient.orm.dao;

import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.ServerResponse;

public interface SageAPIFieldsHandlerDAO {
	
	public ServerResponse addUpdateEmail(Email email);
	public ServerResponse addEmail(Email email);
	public ServerResponse updateEmail(Email email);

}
