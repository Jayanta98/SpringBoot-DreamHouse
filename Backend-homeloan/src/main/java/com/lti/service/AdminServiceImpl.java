package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Account;
import com.lti.entity.Admin;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;
import com.lti.exception.AdminServiceException;
import com.lti.repository.AdminActivityRepository;
import com.lti.repository.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminActivityRepository adminActivityRepository;

	//Application login
	@Override
	public Admin adminLogin(String username, String password) {
		try {
			if(!adminRepository.isAdmin(username)) {
				throw new AdminServiceException("Admin not registered");
			}
			int id = adminRepository.getAdminId(username, password);
			Admin admin = adminRepository.findById(id);
			return admin;
		}
		catch(EmptyResultDataAccessException e) {
			throw new AdminServiceException("Invalid username/password");
		}
	}
	
	
	//List of all applicants
	@Override
	public List<Application> showAllApplications() {
		return adminActivityRepository.getApplicants();
	}
	
	
	//Fetch Application by appId
	@Override
	public Application findByApplicationId(int appId) {
		return adminActivityRepository.applicationDetailById(appId);
	}
	
	
	//List of all properties
	@Override
	public List<Property> getProperty() {
		return adminActivityRepository.getProperty();
	}
	
	
	//Fetch property details by appId
	@Override
	public Property propertyDetailByApplicationId(int appId) {
		return adminActivityRepository.propertyDetailByApplicationId(appId);
	}
	
	
	//List of all income details
	@Override
	public List<Income> getAllIncomeDetails() {
		return adminActivityRepository.getAllIncomeDetails();
	}
	
	
	//Fetch income details by appId
	@Override
	public Income incomeDetailsByApplicationId(int appId) {
		return adminActivityRepository.incomeDetailsByApplicationId(appId);
	}
	
	
	//List of all Loan details
	@Override
	public List<Loan> getAllLoanDetails() {
		return adminActivityRepository.getAllLoanDetails();
	}
	
	
	//Fetch loan details by appId
	@Override
	public Loan loanDetailsByApplicationId(int appId) {
		return adminActivityRepository.loanDetailsByApplicationId(appId);
	}
	
	
	//List of all documents
	@Override
	public List<Document> getAllDocumentDetails() {
		return adminActivityRepository.getAllDocumentDetails();
	}
	
	
	//Fetch document details by appId
	@Override
	public Document documentDetailsByApplicationId(int appId) {
		return adminActivityRepository.documentDetailsByApplicationId(appId);
	}
	
	
	//Update Loan table
	@Override
	public void updateLoanDetails(int appNo, double emi, LocalDate startDate, LocalDate endDate) {
		adminActivityRepository.updateLoanDetailsByAdmin(appNo, emi, startDate, endDate);
	}
	
	
	//Update loan status
	@Override
	public void updateLoanStatus(int appNo, String loanStatus) {
		adminActivityRepository.updateLoanStatusByAdmin(appNo, loanStatus);
	}
	
	
	//Update application status
	@Override
	public void updateApplicationStatus(int appNo, String newStatus) {
		adminActivityRepository.updateApplicationStatus(appNo, newStatus);
	}
	
	
	//List of all account tables
	@Override
	public List<Account> displayAllAccountTables() {
		return adminActivityRepository.displayFullAccountTableByAdmin();
	}
	
	
	//Fetch account detail by appId
	@Override
	public Account displayAccountDetails(int accountNo) {
		return adminActivityRepository.displayAccountDetailsByAdmin(accountNo);
	}
	
	
	//Add account
	@Override
	public void insertAccountDetailsByAdmin(Account account) {
		adminActivityRepository.insertAccountDetailsByAdmin(account);
	}
	
	//Update application
	@Override
	public Application updateApplicationByAdmin(Application application) {
		Application updatedObj = adminActivityRepository.updateApplicationByAdmin(application);
		return updatedObj;
	}
	
	//Update application
	@Override
	public Account updateAccountByAdmin(Account account) {
		Account updatedObj = adminActivityRepository.updateAccountByAdmin(account);
		return updatedObj;
	}
}
