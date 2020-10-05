package com.lti.dto;

public class FormStatus extends Status {

	private int applicationId;
	private int currentForm;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public int getCurrentForm() {
		return currentForm;
	}
	
	public void setCurrentForm(int currentForm) {
		this.currentForm = currentForm;
	}
	
	
}
