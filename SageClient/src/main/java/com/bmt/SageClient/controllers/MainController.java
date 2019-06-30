package com.bmt.SageClient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.sage200api.entities.CustomerViews;

@CrossOrigin
@RestController
public class MainController 
{
	
	@Autowired
	SageAPIHandlerDAO sageAPIHandler;

	
    @RequestMapping("/customerViews")
    public List<CustomerViews> getCustomerViews(@RequestParam(name = "customerName") String customerName) {
        return  sageAPIHandler.requestCustomerViews(customerName);
    }
	
	
}
