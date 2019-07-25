package com.bmt.SageClient.api_dataTypes;

import java.util.List;

import com.bmt.SageClient.sage200api.CustomerMemoListData.MemoListDataTypes;

public  class ListData {
	private List<String> data;
	private Long memoID;
	private boolean shouldBeUpdated;
	private MemoListDataTypes name;
	
	
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public Long getMemoID() {
		return memoID;
	}
	public void setMemoID(Long memoID) {
		this.memoID = memoID;
	}
	public boolean isShouldBeUpdated() {
		return shouldBeUpdated;
	}
	public void setShouldBeUpdated(boolean shouldBeUpdated) {
		this.shouldBeUpdated = shouldBeUpdated;
	}
	public MemoListDataTypes getName() {
		return name;
	}
	public void setName(MemoListDataTypes name) {
		this.name = name;
	}
}