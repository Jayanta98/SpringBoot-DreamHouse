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
	
	Application updateApplication(Application application);
	
	int registerIncome(Income income);
	
	int registerProperty(Property property);
	
	int registerDocuments(Document document);
	
	Account findAccountByAccountNo(int accno);
	
	Application findApplicationById(int appid);
	
	Account findAccountByAppId(int appId);
	
	boolean isAccountPresent(int appId);
	
	boolean isIncomeFormFilled(int applicationId);
	
	boolean isPropertyFormFilled(int applicationId);
	
	boolean isLoanFormFilled(int applicationId);
	
	boolean isDocumentFormFilled(int applicationId);

}