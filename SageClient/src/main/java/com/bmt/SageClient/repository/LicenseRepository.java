package com.bmt.SageClient.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bmt.SageClient.entities.License;

public interface LicenseRepository  extends CrudRepository<License, Integer> {

	@Modifying
	@Query("UPDATE License l SET l.startDate = :startDate, l.expiryDate = :endDate WHERE l.distributionID = :distributionID")
	int setLicenseDates( @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("distributionID") String distributionID);
	
	@Modifying
	@Query("UPDATE License l SET l.expiryDate = :endDate WHERE l.distributionID = :distributionID")
	int setExpiryDate( @Param("endDate") Timestamp endDate, @Param("distributionID") String distributionID);
	
}
