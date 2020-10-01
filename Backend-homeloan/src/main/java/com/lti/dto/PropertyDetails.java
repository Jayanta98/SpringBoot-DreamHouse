package com.lti.dto;

import com.lti.entity.Property;

public class PropertyDetails {

	private int applicationId;
	private Property property;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public Property getProperty() {
		return property;
	}
	
	public void setProperty(Property property) {
		this.property = property;
	}
	
	
}
