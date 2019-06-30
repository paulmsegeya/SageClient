package com.bmt.SageClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.api_dataTypes.CustomerInfoFields;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;

@CrossOrigin
@RestController
public class MainController 
{
	
	@Autowired
	SageAPIHandlerDAO sageAPIHandler;

	
    @RequestMapping("/customer_info")
    public CustomerInfo getCustomerInfo(@RequestParam(name = "customerName") String customerName) {
          return sageAPIHandler.getCustomerInfo(customerName);
    }
    
    @RequestMapping("/customer_info/fields")
    public CustomerInfoFields getCustomerInfoFields() {
          return new CustomerInfoFields();
    }
	
	
}
