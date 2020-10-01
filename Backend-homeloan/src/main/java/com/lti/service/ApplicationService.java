package com.lti.service;

import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Property;

public interface ApplicationService {

	//Application login
	Application applicationLogin(String email, String password);
	
	//Find Application by appId
	Application findById(int id);

	//Checking whether reapplying for loan
	boolean isEligibleToApply(String aadharNo);

	//Application register
	int registerApplication(Application application);

	//Update application
	Application updateApplication(Application application);

	//Track application status
	String trackApplicationStatus(int appId);

	//Add income details
	int registerIncome(Income income);

	//Add property details
	int registerProperty(Property property);

	//Add document details
	int registerDocuments(Document document);

}