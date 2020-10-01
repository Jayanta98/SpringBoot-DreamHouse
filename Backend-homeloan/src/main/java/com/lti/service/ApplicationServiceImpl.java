package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;
import com.lti.exception.ApplicationServiceException;
import com.lti.repository.AdminActivityRepository;
import com.lti.repository.ApplicationRepository;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private AdminActivityRepository adminActivityRepository;

	//Application login
	@Override
	public Application applicationLogin(String email, String password) {
		try {
			if(!applicationRepository.isApplicationExists(email)) {
				throw new ApplicationServiceException("Account not available");
			}
			int id = applicationRepository.getApplicationId(email, password);
			Application application = applicationRepository.findById(id);
			return application;
		}
		catch(EmptyResultDataAccessException e) {
			throw new ApplicationServiceException("Incorrect email/password");
		}
	}
	
	//Find Application by appId
	@Override
	public Application findById(int id) {
		return applicationRepository.findById(id);
	}
	
	
	//Checking whether reapplying for loan
	@Override
	public boolean isEligibleToApply(String aadharNo) {
		if(!applicationRepository.isAadharPresent(aadharNo))
			return true;
		else {
			List<Application> applicationList = applicationRepository.getApplicationListByAadhar(aadharNo);
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for(Application application : applicationList) {
				Loan loan = adminActivityRepository.loanDetailsByApplicationId(application.getApplicationId());
				if(loan.getLoanStatus().equals("Completed") || loan.getLoanStatus().equals("Rejected"))
					ans.add(1);
				else
					ans.add(0);
			}
			if(ans.contains(0))
				return false;
			else
				return true;
		}
	}
	
	
	//Application register
	@Override
	public int registerApplication(Application application) {
		if(isEligibleToApply(application.getAadharNo())) {
			int id = applicationRepository.registerApplication(application);
			return id;
		}
		else {
			throw new ApplicationServiceException("Already applied for loan");
		}
	}
	
	
	//Update application
	@Override
	public Application updateApplication(Application application) {
		return applicationRepository.updateApplication(application);
	}
	
	
	//Track application status
	@Override
	public String trackApplicationStatus(int appId) {
		try {
			String status = applicationRepository.applicationStatus(appId);
			return status;
		}
		catch(EmptyResultDataAccessException e) {
			throw new ApplicationServiceException("Invalid Application Id");
		}
	}
	
	
	//Add income details
	@Override
	public int registerIncome(Income income) {
		return applicationRepository.registerIncome(income);
	}
	
	
	//Add property details
	@Override
	public int registerProperty(Property property) {
		return applicationRepository.registerProperty(property);
	}
	
	
	//Add document details
	@Override
	public int registerDocuments(Document document) {
		return applicationRepository.registerDocuments(document);
	}
	
	
	
}
