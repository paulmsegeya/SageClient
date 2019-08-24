package com.bmt.SageClient.orm.dao;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.bmt.SageClient.GlobalVars;

public class RequestHeaders 
{
	boolean isConnDetailsSet;
	protected HttpHeaders headers;

	public RequestHeaders() {
		isConnDetailsSet = false;
		headers = new HttpHeaders();
		setConnectionDetails();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	} 
	
	
	private void setConnectionDetails()
	{
		TimerTask checkDetailsTask = new TimerTask() {
	        public void run() {
	        	if(!GlobalVars.accessToken.equals("") && !GlobalVars.subscriptionKey.equals("") && !GlobalVars.SageAPICompanyID.equals("") && !GlobalVars.siteID.equals("")) {
	        		headers.set("Authorization", "Bearer " + GlobalVars.accessToken);		
	        		headers.set("ocp-apim-subscription-key", GlobalVars.subscriptionKey); 
	        		headers.set("X-Company",  GlobalVars.SageAPICompanyID); 
	        		headers.set("X-Site", GlobalVars.siteID);
	        		isConnDetailsSet = true;
	        		cancel();
	        	}

	        	
	        }
	    };
	    Timer timer = new Timer("Timer");
	     
	    long delay = 0;
	    long period = 1000L;
	    timer.scheduleAtFixedRate(checkDetailsTask, delay, period);
	}
	
	
}
