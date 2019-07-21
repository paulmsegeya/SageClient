package com.bmt.SageClient.entities;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Users 
{
	 	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Integer id;
	    private String name;
	    private String roll;
	    private Boolean hasAdminRights;
	    private String userName;
		private String password;
	    
		
	    @Id
	    @Column(name = "ID", nullable = false)
		public Integer getId() {
			return id;
		}
	    
	    public void setId(Integer id) {
	    	this.id  = id;
	    }

	    @Column(name = "NAME")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	    @Column(name = "ROLL")
		public String getRoll() {
			return roll;
		}

		public void setRoll(String roll) {
			this.roll = roll;
		}

	    @Column(name = "HAS_ADMIN_RIGHTS", nullable = false)
		public Boolean getHasAdminRights() {
			return hasAdminRights;
		}

		public void setHasAdminRights(Boolean hasAdminRights) {
			this.hasAdminRights = hasAdminRights;
		}

	    @Column(name = "USER_NAME", nullable = false)
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

	    @Column(name = "PASSWORD", nullable = false)
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


}
