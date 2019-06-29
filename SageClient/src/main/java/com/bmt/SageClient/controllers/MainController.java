package com.bmt.SageClient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<CustomerViews> getCustomerViews() {
        return  sageAPIHandler.requestCustomerViews("customer_views?$filter=reference%20eq%20'00001001'");
    }
	
	
}
