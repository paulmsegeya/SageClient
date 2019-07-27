package com.bmt.SageClient.api_dataTypes;

import java.util.List;

import com.bmt.SageClient.sage200api.CustomerMemoListData.MemoListDataTypes;

public class CustomerListData
{	
	private Long customerID;
	private ListData interviews;
	private ListData chosenAgencies;
	private ListData offers;
	private ListData casting;
	private ListData selfTapes;	
	
	
	public CustomerListData() {
		this.customerID = null;
		this.interviews = new ListData(MemoListDataTypes.INTERVIEWS);
		this.chosenAgencies = new ListData(MemoListDataTypes.CHOSEN_AGENCIES);
		this.offers = new ListData(MemoListDataTypes.OFFERS);
		this.casting = new ListData(MemoListDataTypes.CASTING_DIRECTORIES);
		this.selfTapes = new ListData(MemoListDataTypes.SELF_TAPES);
	}
	
	public Long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public ListData getInterviews() {
		return interviews;
	}
	public void setInterviews(ListData interviews) {
		this.interviews = interviews;
	}
	public ListData getChosenAgencies() {
		return chosenAgencies;
	}
	public void setChosenAgencies(ListData chosenAgencies) {
		this.chosenAgencies = chosenAgencies;
	}
	public ListData getOffers() {
		return offers;
	}
	public void setOffers(ListData offers) {
		this.offers = offers;
	}
	public ListData getCasting() {
		return casting;
	}
	public void setCasting(ListData casting) {
		this.casting = casting;
	}
	public ListData getSelfTapes() {
		return selfTapes;
	}
	public void setSelfTapes(ListData selfTapes) {
		this.selfTapes = selfTapes;
	}
	
	
	
	
	
	/*/List Data Setters
	public void setInterviewsData(List<String> data) {
		this.interviews.setData(data);
	}
	public void setChosenAgenciesData(List<String> data) {
		this.chosenAgencies.setData(data);
	}
	public void setOfferData(List<String> data) {
		this.offers.setData(data);
	}
	public void setCastingData(List<String> data) {
		this.casting.setData(data);
	}
	public void setSelfTapesData(List<String> data) {
		this.selfTapes.setData(data);
	} */
	
	

}
