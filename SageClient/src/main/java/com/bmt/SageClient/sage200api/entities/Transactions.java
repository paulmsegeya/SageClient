package com.bmt.SageClient.sage200api.entities;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transactions 
{
	
	private long id;
	private long customerId;
	private String tradeTransactionType;
	private String reference;
	private Timestamp transactionDate;
	private int documentGrossValue;
	private int documentOutstandingValue;
	
	

	@JsonProperty("id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty("customer_id")
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("trader_transaction_type")
	public String getTradeTransactionType() {
		return tradeTransactionType;
	}
	public void setTradeTransactionType(String tradeTransactionType) {
		this.tradeTransactionType = tradeTransactionType;
	}

	@JsonProperty("reference")
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	@JsonProperty("transaction_date")
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	@JsonProperty("document_gross_value")
	public int getDocumentGrossValue() {
		return documentGrossValue;
	}
	public void setDocumentGrossValue(int documentGrossValue) {
		this.documentGrossValue = documentGrossValue;
	}

	@JsonProperty("document_outstanding_value")
	public int getDocumentOutstandingValue() {
		return documentOutstandingValue;
	}
	public void setDocumentOutstandingValue(int documentOutstandingValue) {
		this.documentOutstandingValue = documentOutstandingValue;
	}
	

	

}
