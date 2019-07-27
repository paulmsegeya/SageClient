package com.bmt.SageClient;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/*import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.bmt.SageClient.api_dataTypes.CustomerInfo;
import com.bmt.SageClient.api_dataTypes.LicenseForm;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.entities.Users;
import com.bmt.SageClient.orm.dao.LicenseDAO;
import com.bmt.SageClient.orm.dao.SageAPICustomerHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;
import com.bmt.SageClient.orm.dao.SageAPIMemoHandlerDAO;
import com.bmt.SageClient.orm.dao.UserDAO;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;



@RunWith(SpringRunner.class)
@SpringBootTest
public class NotesTests {

	@Autowired
	SageAPIMemoHandlerDAO memoHandler;
	
	@Test
	public void contextLoads() {
	} 
	
	

	
}

