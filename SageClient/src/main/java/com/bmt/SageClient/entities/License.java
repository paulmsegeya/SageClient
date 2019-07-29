package com.bmt.SageClient.entities;

import javax.persistence.Entity;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class License 
{
	 	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer id;
	    private String distributionID; 
	    @JsonFormat(pattern="yyyy-MM-dd")  
	    private Timestamp startDate;
	    @JsonFormat(pattern="yyyy-MM-dd")  
	    private Timestamp expiryDate;
	    
		
	    @Id
	    @Column(name = "ID", nullable = false)
		public Integer getId() {
			return id;
		}
	    
	    public void setId(Integer id) {
	    	this.id  = id;
	    }


	    @Column(name = "DISTRIBUTION_ID", nullable = false)
		public String getDistributionID() {
			return distributionID;
		}

		public void setDistributionID(String distributionID) {
			this.distributionID = distributionID;
		}


	    @Column(name = "START_DATE")
		public Timestamp getStartDate() {
			return startDate;
		}

		public void setStartDate(Timestamp startDate) {
			this.startDate = startDate;
		}


	    @Column(name = "END_DATE ")
		public Timestamp getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Timestamp expiryDate) {
			this.expiryDate = expiryDate;
		}


}
