package com.lti.dto;

public class PropertyFields extends Status {

	private int propertyId;
	private String propertyLocation;
	private String propertyName;
	private double estimatedAmount;
	private int applicationId;
	
	public int getPropertyId() {
		return propertyId;
	}
	
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	
	public String getPropertyLocation() {
		return propertyLocation;
	}
	
	public void setPropertyLocation(String propertyLocation) {
		this.propertyLocation = propertyLocation;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public double getEstimatedAmount() {
		return estimatedAmount;
	}
	
	public void setEstimatedAmount(double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	
}
