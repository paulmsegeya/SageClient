package com.bmt.SageClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import com.bmt.SageClient.orm.dao.SageApiConnectionDAO;

@Repository
public class Initialiser implements InitialiserInterface
{	
	@Autowired
	SageApiConnectionDAO sageApiConnDAO;
	
	public Initialiser() {
		//setSageApiConnectionData();
	}
	
	
	
	@Override
	public void setSageApiConnectionData()
	{
		boolean isConnectionDataFound = sageApiConnDAO.getConnectionDetails();
		System.out.println(isConnectionDataFound);
	}

}
