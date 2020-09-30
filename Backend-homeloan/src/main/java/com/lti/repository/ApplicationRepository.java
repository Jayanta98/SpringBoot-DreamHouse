package com.lti.repository;

import java.util.List;

import com.lti.entity.Account;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Property;

public interface ApplicationRepository {
	
	int registerApplication(Application application);

	boolean isApplicationExists(String email);

	int getApplicationId(String email, String password);

	String applicationStatus(int appNo);
	
	Application findById(int id);

	Account displayAccountDetails(int accountNo);
	
	boolean isAadharPresent(String aadharNo);
	
	List<Application> getApplicationListByAadhar(String aadharNo);
	
	void updateApplication(Application application);
	
	int registerIncome(Income income);
	
	int registerProperty(Property property);
	
	int registerDocuments(Document document);

}