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
import com.bmt.SageClient.api_dataTypes.Email;
import com.bmt.SageClient.api_dataTypes.ListDataForm;
import com.bmt.SageClient.api_dataTypes.Note;
import com.bmt.SageClient.api_dataTypes.SageConnectionTest;
import com.bmt.SageClient.api_dataTypes.SageInterfaceConnection;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;
import com.bmt.SageClient.orm.dao.SageAPICustomerHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIEmailSearchDAO;
import com.bmt.SageClient.orm.dao.SageAPIFieldsHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIMemoHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPITelSearchDAO;

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
	@Autowired
	SageAPIFieldsHandlerDAO sageAPIFieldsHandlerDAO;
	@Autowired
	SageAPITelSearchDAO sageApiTelSearchDao;
	@Autowired
	SageAPIEmailSearchDAO sageAPIEmailSearchDAO;

	
	
	
	@RequestMapping("/search/tel/names")
    public List<String> searchNamesByTel(@RequestParam(name = "tel", required=true) String tel) {
          return sageApiTelSearchDao.seachViaTel(tel);
    }
	
	@RequestMapping("/search/email/names")
    public List<String> searchNamesByEmail(@RequestParam(name = "email", required=true) String email) {
          return sageAPIEmailSearchDAO.seachViaEmail(email);
    }
    
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
    
    @PostMapping("/add/all-list-data")
    public List<ServerResponse> addUpdateListData(@RequestBody(required=true) CustomerListData listData) {
          return sageAPMemoIHandler.addUpdateListData(listData);
    } 
	
    @PostMapping("/add/list_data")
    public ServerResponse addUpdateListData(@RequestBody(required=true) ListDataForm listDataForm) {
          return sageAPMemoIHandler.addUpdateListData(listDataForm);
    }
    
    @PostMapping("/add/notes")
    public List<ServerResponse> addUpdateNotes(@RequestBody(required=true) List<Note> memos) {
          return sageAPMemoIHandler.CUDNotes(memos);
    }
    
    @PostMapping("/add/email")
    public ServerResponse addUpdateEmail(@RequestBody(required=true) Email email) {
          return sageAPIFieldsHandlerDAO.addUpdateEmail(email);
    }
    
    @PostMapping("/add/tel")
    public ServerResponse addUpdateTel(@RequestBody(required=true) Telephone tel) {
          return sageAPIFieldsHandlerDAO.addUpdateTel(tel);
    }
    
    
}
