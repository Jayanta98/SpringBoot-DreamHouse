package com.lti.repository;

import java.time.LocalDate;
import java.util.List;


import com.lti.entity.Account;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;

public interface AdminActivityRepository {

	//Inserting Account Details For Particular Application By Admin
	void insertAccountDetailsByAdmin(Account account);

	//See Account details by accountNo byAdmin
	Account displayAccountDetailsByAdmin(int accountNo);

	//Full Account Table Seen by Admin
	List<Account> displayFullAccountTableByAdmin();

	//Status Update by admin from pending to <some value>
	void updateApplicationStatus(int appNo, String newStatus);

	//Update Loan Status by Admin
	void updateLoanStatusByAdmin(int appNo, String loanStatus);

	void updateLoanDetailsByAdmin(int appNo, double emi, LocalDate startDate, LocalDate endDate);

	//Get all applications list
	List<Application> getApplicants();

	//Fetch single application detail by Id
	Application applicationDetailById(int applicationId);

	//Get all properties list
	List<Property> getProperty();

	//Fetch single property detail by ApplicationId
	Property propertyDetailByApplicationId(int appId);

	//Get all incomes list
	List<Income> getAllIncomeDetails();

	//Fetch income detail by ApplicationId
	Income incomeDetailsByApplicationId(int appId);

	//Get all loans list
	List<Loan> getAllLoanDetails();

	//Fetch loan detail by ApplicationId
	Loan loanDetailsByApplicationId(int appId);

	//Get all documents list
	List<Document> getAllDocumentDetails();

	//Fetch document detail by ApplicationId
	Document documentDetailsByApplicationId(int appId);

	Application updateApplicationByAdmin(Application application);

	Account updateAccountByAdmin(Account account);

}