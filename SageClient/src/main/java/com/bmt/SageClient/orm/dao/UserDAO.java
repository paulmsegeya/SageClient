package com.bmt.SageClient.orm.dao;

import com.bmt.SageClient.entities.Users;

public interface UserDAO 
{
	
	public Users getUser(String userName);
	public boolean authenticateUser(String userName, String password);

}
