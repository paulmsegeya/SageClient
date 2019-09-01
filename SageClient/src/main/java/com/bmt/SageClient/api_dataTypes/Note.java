package com.bmt.SageClient.api_dataTypes;

import java.util.Date;

public class Note
{
	private long id;
	private long customerId;
	private String note;
	private Date dateTimeUpdated;
	private boolean shouldBeDeleted;

	public Note() {}
	
	public Note(long id, long customerId, String note, Date dateTimeUpdated) {
		this.id = id;
		this.customerId = customerId;
		this.note = note;
		this.dateTimeUpdated = dateTimeUpdated;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isShouldBeDeleted() {
		return shouldBeDeleted;
	}
	public void setShouldBeDeleted(boolean shouldBeDeleted) {
		this.shouldBeDeleted = shouldBeDeleted;
	}
	public Date getDateTimeUpdated() {
		return dateTimeUpdated;
	}

	public void setDateTimeUpdated(Date dateTimeUpdated) {
		this.dateTimeUpdated = dateTimeUpdated;
	}
	
	

}
