package com.bmt.SageClient.orm.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.entities.Users;
import com.bmt.SageClient.orm.dao.UserDAO;
import com.bmt.SageClient.repository.LicenseRepository;
import com.bmt.SageClient.repository.UserRepository;

@Repository
public class UserDAOImpl implements UserDAO 
{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Users getUser(String userName) {
		Users user = userRepo.findTopByUserName(userName);
		if(user == null) return new Users();
		return user;
	}

	
	@Override
	public boolean authenticateUser(String userName, String password) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		Users user = userRepo.findTopByUserName(userName);
		return passEncoder.matches(password, user.getPassword());
	}
	


}
