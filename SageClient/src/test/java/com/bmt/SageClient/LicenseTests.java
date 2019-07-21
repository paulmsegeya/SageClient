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
import com.bmt.SageClient.orm.dao.UserDAO;
import com.bmt.SageClient.sage200api.entities.CustomerMemos;
import com.bmt.SageClient.sage200api.entities.CustomerViews;
import com.bmt.SageClient.sage200api.entities.Customers;
import com.bmt.SageClient.sage200api.entities.CustomersContacts;
import com.bmt.SageClient.sage200api.entities.Transactions;



@RunWith(SpringRunner.class)
@SpringBootTest
public class LicenseTests {

	@Autowired
	UserDAO userDao;
	@Autowired
	LicenseDAO licenseDAO;
	
	@Test
	public void contextLoads() {
	} 
	
	
	@Test
	public void testGetUserByUserName()
	{		
		Users user = userDao.getUser("bobSmithUser");
		System.out.println(user.getName());
	}
	
	@Test
	public void testAuthenticateUser()
	{		
		boolean isAuthenticated = userDao.authenticateUser("bobSmithUser", "Password");
		System.out.println(isAuthenticated);
	}
	
	@Test
	public void testPasswordEncode()
	{
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		String pass = passEncoder.encode("Password");
		System.out.println(pass);
	}
	
	
	@Test
	public void testSetLicenseDates()
	{
		LicenseForm form = new LicenseForm();
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2020, 7, 22, 0, 0, 0);
		long endDateTime = calendar.getTimeInMillis();		
		calendar.set(2019, 7, 22, 0, 0, 0);
		long startDateTime = calendar.getTimeInMillis();		

		form.setEndDate(new Timestamp(endDateTime));
		form.setStartDate(new Timestamp(startDateTime));
		form.setDistributionID("ebe53ed0-e2d9-4fdb-a220-cbd8468710b3");
		
		ServerResponse serverResponse = licenseDAO.setLicenseDates(form, "bobSmithUser", "S0lar2014");
		System.out.println(serverResponse.getMessage());
	}
	
	
	@Test
	public void testSetExpiryDate()
	{
		LicenseForm form = new LicenseForm();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 7, 22, 0, 0, 0);
		form.setEndDate(new Timestamp(calendar.getTimeInMillis()));
		form.setDistributionID("ebe53ed0-e2d9-4fdb-a220-cbd8468710b3");
		ServerResponse serverResponse = licenseDAO.setExpiryDate(form, "bobSmithUser", "S0lar2014");
		System.out.println(serverResponse.getMessage());
	}
	
}

