package com.bmt.SageClient.orm.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bmt.SageClient.api_dataTypes.LicenseForm;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.LicenseDAO;
import com.bmt.SageClient.orm.dao.UserDAO;
import com.bmt.SageClient.repository.LicenseRepository;

@Repository
public class LicenseDAOImpl implements LicenseDAO 
{

	@Autowired
	LicenseRepository licenseRepo;
	@Autowired
	UserDAO userDAO;

	@Override
	@Transactional
	public ServerResponse setLicenseDates(LicenseForm form, String userName, String password) 
	{		
		ServerResponse serverResponse = new ServerResponse();
		if(userDAO.authenticateUser(userName, password))
		{
			int totalRowsUpdated = licenseRepo.setLicenseDates(form.getStartDate(), form.getEndDate(), form.getDistributionID());
			if(totalRowsUpdated > 0) {
				serverResponse.setMessage("License updated");				
				serverResponse.setSuccess(true);
			}
			else {
				serverResponse.setMessage("Error updating license");
				serverResponse.setSuccess(false);
			}
		}
		else {
			serverResponse.setMessage("Could not authorize user");
			serverResponse.setSuccess(false);
		}		
		return serverResponse;
	}
	
	

	@Override
	@Transactional
	public ServerResponse setExpiryDate(LicenseForm form, String userName, String password) 
	{
		ServerResponse serverResponse = new ServerResponse();
		if(userDAO.authenticateUser(userName, password))
		{
			int totalRowsUpdated = licenseRepo.setExpiryDate(form.getEndDate(), form.getDistributionID());
			if(totalRowsUpdated > 0) {
				serverResponse.setMessage("Expiry date updated");				
				serverResponse.setSuccess(true);
			}
			else {
				serverResponse.setMessage("Error updating expiry date");
				serverResponse.setSuccess(false);
			}
		}
		else {
			serverResponse.setMessage("Could not authorize user");
			serverResponse.setSuccess(false);
		}		
		return serverResponse;
		
	}




}
