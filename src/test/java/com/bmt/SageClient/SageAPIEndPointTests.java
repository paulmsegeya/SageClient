package com.bmt.SageClient;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.bmt.SageClient.orm.dao.SageAPIHandlerDAO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SageAPIEndPointTests {

	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	SageAPIHandlerDAO sageAPIHandler;
	
	
	@Test
	public void contextLoads() {
		
		
	}
	

	
	@Test
	public void testRequestCustomerViews()
	{
		sageAPIHandler.requestCustomerViews("customer_views?$filter=reference%20eq%20'00001001'");
	}
}
