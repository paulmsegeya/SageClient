package com.bmt.SageClient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.api_dataTypes.CustomerInfoFields;
import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.SageConnectionTest;
import com.bmt.SageClient.api_dataTypes.SageInterfaceConnection;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.SageAPICustomerHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIMemoHandlerDAO;

@CrossOrigin
@RestController
public class MainController 
{
	
	@Autowired
	SageAPICustomerHandlerDAO sageAPICustomerHandler;
	@Autowired
	SageAPIHandlerDAO sageAPIHandler;
	@Autowired
	SageAPIMemoHandlerDAO sageAPMemoIHandler;

	
    @RequestMapping("/customer_info")
    public CustomerInfo getCustomerInfo(@RequestParam(name = "customerName", required=true) String customerName) {
          return sageAPICustomerHandler.getCustomerInfo(customerName);
    }
    
    @RequestMapping("/customer_info/fields")
    public CustomerInfoFields getCustomerInfoFields() {
          return new CustomerInfoFields();
    }
    
    @RequestMapping("/customers/names")
    public List<String> getCustomerNames(@RequestParam(name = "customerNamePart", required=true ) String customerNamePart) {
          return sageAPICustomerHandler.requestCustomerNames(customerNamePart);
    }
    
    @RequestMapping("/test_sageAPI_connection")
    public SageConnectionTest testSageAPIConnection() {
          return sageAPIHandler.testSageAPIConnection();
    }   
    
    @RequestMapping("/test_sage_interface_connection")
    public SageInterfaceConnection testSageInterfaceConnection() {
          return sageAPIHandler.testSageInterfaceConnection();
    }    
    
    @PostMapping("/addNotes")
    public List<ServerResponse> addUpdateListData(@RequestBody(required=true) CustomerListData listData) {
          return sageAPMemoIHandler.addUpdateListData(listData);
    }
	
	
}