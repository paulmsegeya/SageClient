package com.bmt.SageClient.api_dataTypes;

public class ServerResponse 
{
	private boolean isSuccess;
	private String httpStatus;
	private String message;
	private String errorSource;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorSource() {
		return errorSource;
	}
	public void setErrorSource(String errorSource) {
		this.errorSource = errorSource;
	}

}
