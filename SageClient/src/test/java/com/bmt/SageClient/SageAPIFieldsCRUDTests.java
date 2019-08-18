package com.bmt.SageClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.api_dataTypes.Telephone;
import com.bmt.SageClient.orm.dao.SageAPIFieldsHandlerDAO;
import com.bmt.SageClient.service.TokenGetter;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SageAPIFieldsCRUDTests {

	@Autowired
	SageAPIFieldsHandlerDAO sageAPIFields;
	
	@Test
	public void contextLoads() {
	} 
	
	
	@Test
	public void testUpdateTel()
	{		
		TokenGetter.getToken();
		TokenGetter.readToken();
		Telephone telephone = new Telephone();
		telephone.setId(15243042);
		telephone.setTelephone("07981111114");
		ServerResponse response = sageAPIFields.addUpdateTel(telephone);
		System.out.println(response.getMessage());
	}
	

}

