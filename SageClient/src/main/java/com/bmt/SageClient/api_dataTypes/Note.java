package com.bmt.SageClient.api_dataTypes;

public class Note
{
	private long id;
	private long customerId;
	private String note;
	private boolean shouldBeDeleted;

	public Note() {}
	
	public Note(long id, long customerId, String note) {
		this.id = id;
		this.customerId = customerId;
		this.note = note;
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
	
	

}
