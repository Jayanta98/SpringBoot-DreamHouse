package com.lti.dto;

public class UserLoginStatus extends Status {

	private int applicationId;
	private String name;
	private String applicationStatus;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getApplicationStatus() {
		return applicationStatus;
	}
	
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	
}
