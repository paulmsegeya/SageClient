package com.bmt.SageClient.api_dataTypes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustomerInfo 
{
	private Long customerID;
	private String accountRef;
	private String accountName;
	private String shortName;
	private String customerName;
	private Timestamp dateOfShoot;
	private String transPackage;
	private int total;
	private int invoiceBalance;
	private String signedDate;
	private String type;
	private Email email;
	private Email email2;
	private Telephone tel;
	private String seenContact;
	private List<Note> memos = new ArrayList<>();
	private CustomerListData listData;
	
	
	
	public Long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public String getAccountRef() {
		return accountRef;
	}
	public void setAccountRef(String accountRef) {
		this.accountRef = accountRef;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Timestamp getDateOfShoot() {
		return dateOfShoot;
	}
	public void setDateOfShoot(Timestamp dateOfShoot) {
		this.dateOfShoot = dateOfShoot;
	}
	public String getTransPackage() {
		return transPackage;
	}
	public void setTransPackage(String transPackage) {
		this.transPackage = transPackage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getInvoiceBalance() {
		return invoiceBalance;
	}
	public void setInvoiceBalance(int invoiceBalance) {
		this.invoiceBalance = invoiceBalance;
	}
	public String getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public Email getEmail2() {
		return email2;
	}
	public void setEmail2(Email email2) {
		this.email2 = email2;
	}
	public Telephone getTel() {
		return tel;
	}
	public void setTel(Telephone tel) {
		this.tel = tel;
	}
	public String getSeenContact() {
		return seenContact;
	}
	public void setSeenContact(String seenContact) {
		this.seenContact = seenContact;
	}
	public List<Note> getMemos() {
		return memos;
	}
	public void setMemos(List<Note> memos) {
		this.memos = memos;
	}
	public CustomerListData getListData() {
		return listData;
	}
	public void setListData(CustomerListData listData) {
		this.listData = listData;
	}
	
	public void addMemo(Note memo) {
		memos.add(memo);
	}

	

}
