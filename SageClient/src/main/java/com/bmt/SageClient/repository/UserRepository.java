package com.bmt.SageClient.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bmt.SageClient.entities.Users;

public interface UserRepository  extends CrudRepository<Users, Integer> {
	
	
	@Query("SELECT u FROM Users u WHERE u.userName = :userName")
	Users findUserByStatusAndNameNamedParams( @Param("userName") String userName);

}
