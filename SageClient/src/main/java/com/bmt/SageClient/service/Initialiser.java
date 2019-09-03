package com.bmt.SageClient.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import com.bmt.SageClient.orm.dao.SageApiConnectionDAO;

@Repository
public class Initialiser implements InitialiserInterface
{	
	@Autowired
	SageApiConnectionDAO sageApiConnDAO;
	
	Process startFrontEndProcess;
	
	public Initialiser() {
		//setSageApiConnectionData();
	}
	
	
	
	@Override
	public void setSageApiConnectionData()
	{
		boolean isConnectionDataFound = sageApiConnDAO.getConnectionDetails();
		System.out.println(isConnectionDataFound);
	}
	
	@Override
	public void startFrontEnd(){		
		 try {
			 startFrontEndProcess = Runtime.getRuntime().exec( System.getProperty("user.dir") + "/startFrontEnd.bat", null, new java.io.File(System.getProperty("user.dir")));			 	
			 terminateFrontEnd();
		    } catch (IOException  e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void terminateFrontEnd(){		
		startFrontEndProcess.destroy();
	}

}
