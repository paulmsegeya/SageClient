package com.bmt.SageClient.entities;

import javax.persistence.Entity;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class SageApiConnection 
{
	 	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer id;
	    private String subscriptionKey; 
	    private long  companyID;
	    private String  siteID;
	    
		
	    @Id
	    @Column(name = "ID", nullable = false)
		public Integer getId() {
			return id;
		}
	    
	    public void setId(Integer id) {
	    	this.id  = id;
	    }

	    @Column(name = "SUBSCRIPTION_KEY", nullable = false)
		public String getSubscriptionKey() {
			return subscriptionKey;
		}

		public void setSubscriptionKey(String subscriptionKey) {
			this.subscriptionKey = subscriptionKey;
		}

		@Column(name = "COMPANY_ID", nullable = false)
		public long getCompanyID() {
			return companyID;
		}

		public void setCompanyID(long companyID) {
			this.companyID = companyID;
		}

		@Column(name = "SITE_ID", nullable = false)
		public String getSiteID() {
			return siteID;
		}

		public void setSiteID(String siteID) {
			this.siteID = siteID;
		}


	  


}
