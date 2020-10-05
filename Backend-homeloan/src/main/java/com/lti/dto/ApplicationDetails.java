package com.lti.dto;

import com.lti.entity.Application;

public class ApplicationDetails {
	
	Application application;
	boolean applicationStatus;
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public boolean isApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(boolean applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	

}
