package com.bmt.SageClient.orm.dao;

import com.bmt.SageClient.api_dataTypes.LicenseForm;
import com.bmt.SageClient.api_dataTypes.ServerResponse;

public interface LicenseDAO 
{
	
	public ServerResponse setLicenseDates(LicenseForm form, String userName, String password);
	public ServerResponse setExpiryDate(LicenseForm form, String userName, String password);

}
