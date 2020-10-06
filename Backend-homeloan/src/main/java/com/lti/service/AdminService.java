package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import com.lti.entity.Account;
import com.lti.entity.Admin;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;

public interface AdminService {

	//Application login
	Admin adminLogin(String username, String password);

	//List of all applicants
	List<Application> showAllApplications();

	//Fetch Application by appId
	Application findByApplicationId(int appId);

	//List of all properties
	List<Property> getProperty();

	//Fetch property details by appId
	Property propertyDetailByApplicationId(int appId);

	//List of all income details
	List<Income> getAllIncomeDetails();

	//Fetch income details by appId
	Income incomeDetailsByApplicationId(int appId);

	//List of all Loan details
	List<Loan> getAllLoanDetails();

	//Fetch loan details by appId
	Loan loanDetailsByApplicationId(int appId);

	//List of all documents
	List<Document> getAllDocumentDetails();

	//Fetch document details by appId
	Document documentDetailsByApplicationId(int appId);

	//Update Loan table
	void updateLoanDetails(int appNo, double emi, LocalDate startDate, LocalDate endDate);

	//Update loan status
	void updateLoanStatus(int appNo, String loanStatus);

	//Update application status
	void updateApplicationStatus(int appNo, String newStatus);

	//List of all account tables
	List<Account> displayAllAccountTables();

	//Fetch account detail by appId
	Account displayAccountDetails(int accountNo);

	//Add account
	void insertAccountDetailsByAdmin(Account account);

	Application updateApplicationByAdmin(Application application);

	Account updateAccountByAdmin(Account account);

}