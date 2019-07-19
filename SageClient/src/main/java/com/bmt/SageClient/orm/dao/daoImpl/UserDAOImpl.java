package com.bmt.SageClient.orm.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bmt.SageClient.entities.Users;
import com.bmt.SageClient.orm.dao.UserDAO;
import com.bmt.SageClient.repository.UserRepository;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Users getUser(String userName) 
	{
		
		return null;
	}

}
