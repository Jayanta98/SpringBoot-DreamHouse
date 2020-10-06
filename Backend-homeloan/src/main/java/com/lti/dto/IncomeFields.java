package com.lti.dto;

public class IncomeFields extends Status {

	private int incomeId;
	private String typeOfEmployement;
	private String organizationType;
	private String employerName;
	private int applicationId;
	
	public int getIncomeId() {
		return incomeId;
	}
	
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	
	public String getTypeOfEmployement() {
		return typeOfEmployement;
	}
	
	public void setTypeOfEmployement(String typeOfEmployement) {
		this.typeOfEmployement = typeOfEmployement;
	}
	
	public String getOrganizationType() {
		return organizationType;
	}
	
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	
	public String getEmployerName() {
		return employerName;
	}
	
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	
	
}
