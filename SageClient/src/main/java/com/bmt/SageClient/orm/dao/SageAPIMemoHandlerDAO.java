package com.bmt.SageClient.orm.dao;

import java.util.List;

import com.bmt.SageClient.api_dataTypes.CustomerListData;
import com.bmt.SageClient.api_dataTypes.ListData;
import com.bmt.SageClient.api_dataTypes.ListDataForm;
import com.bmt.SageClient.api_dataTypes.Note;
import com.bmt.SageClient.api_dataTypes.ServerResponse;

public interface SageAPIMemoHandlerDAO 
{
	public List<ServerResponse> addUpdateListData(CustomerListData listData);
	public ServerResponse addUpdateListData(ListDataForm listDataForm);
	public ServerResponse updateListData(ListData listData );
	public ServerResponse addListData(Long customerID, ListData listData );

	public List<ServerResponse> CUDNotes(List<Note> notes);
	public ServerResponse addNotes(Note notes);
	public ServerResponse updateNote(Note notes);
	public ServerResponse deleteNotes(Note notes);
	

}
