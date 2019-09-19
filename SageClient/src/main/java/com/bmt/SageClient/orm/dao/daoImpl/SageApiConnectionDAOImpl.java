package com.bmt.SageClient.orm.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bmt.SageClient.GlobalVars;
import com.bmt.SageClient.api_dataTypes.LicenseForm;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.entities.SageApiConnection;
import com.bmt.SageClient.orm.dao.LicenseDAO;
import com.bmt.SageClient.orm.dao.SageApiConnectionDAO;
import com.bmt.SageClient.orm.dao.UserDAO;
import com.bmt.SageClient.repository.LicenseRepository;
import com.bmt.SageClient.repository.SageApiConnectionRepository;

@Repository
public class SageApiConnectionDAOImpl implements SageApiConnectionDAO 
{

	@Autowired
	SageApiConnectionRepository sageApiConnRepo;
	
	
	@Override
	@Transactional
	public boolean getConnectionDetails()
	{

		Iterable<SageApiConnection> connectionRows =  sageApiConnRepo.findAll();
		boolean isDetailsFound = false;
		for(SageApiConnection connectionDetails: connectionRows) 
		{
			isDetailsFound = true;
			GlobalVars.subscriptionKey = connectionDetails.getSubscriptionKey();
			GlobalVars.siteID = connectionDetails.getSiteID();
			GlobalVars.SageAPICompanyID = Long.toString(connectionDetails.getCompanyID());
		}
		return isDetailsFound;
	}


	




}
