package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AccountDetail;
import com.lti.dto.AdminLogin;
import com.lti.dto.AdminLoginStatus;
import com.lti.dto.ApplicationDetails;
import com.lti.dto.CreateAccountDetailsByAdmin;
import com.lti.dto.LoanDetails;
import com.lti.dto.Status;
import com.lti.entity.Account;
import com.lti.entity.Admin;
import com.lti.entity.Application;
import com.lti.entity.Loan;
import com.lti.exception.AdminServiceException;
import com.lti.service.AdminService;
import com.lti.service.ApplicationService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping(path = "/admin-login")
	public AdminLoginStatus adminLogin(@RequestBody AdminLogin adminLogin) {
		try {
			Admin admin = adminService.adminLogin(adminLogin.getUsername(), adminLogin.getPassword());
			AdminLoginStatus adminLoginStatus = new AdminLoginStatus();
			adminLoginStatus.setStatus(true);
			adminLoginStatus.setAdminId(admin.getId());
			adminLoginStatus.setName(admin.getFirstName() + " " + admin.getLastName());
			adminLoginStatus.setStatusMessage("Admin login successfull");
			return adminLoginStatus;
		}
		catch(AdminServiceException e) {
			AdminLoginStatus adminLoginStatus = new AdminLoginStatus();
			adminLoginStatus.setStatus(false);
			adminLoginStatus.setStatusMessage(e.getMessage());
			return adminLoginStatus;
		}
	}
	
	@GetMapping(path = "/view-all-applications")
	public List<ApplicationDetails> viewAllApplications() {
		
		List<ApplicationDetails> appList = new ArrayList<ApplicationDetails>();
		List<Application> list = adminService.showAllApplications();
		
		for(Application application : list ) {
			ApplicationDetails appDetails = new ApplicationDetails();
			if(applicationService.isAccountPresent(application.getApplicationId())) {
				Account account = applicationService.fetchAccountByAppId(application.getApplicationId());
				appDetails.setAccountNo(account.getAccountNo());
			}
			appDetails.setFirstname(application.getFirstname());
			appDetails.setLastname(application.getLastname());
			appDetails.setPhoneNo(application.getPhoneNo());
			appDetails.setDateOfBirth(application.getDateOfBirth());
			appDetails.setDateOfAppointment(application.getDateOfAppointment());
			appDetails.setAadharNo(application.getAadharNo());
			appDetails.setPanNo(application.getPanNo());
			appDetails.setApplicationStatusMessage(application.getApplicationStatus());
			appDetails.setEmail(application.getEmail());
			appDetails.setNationality(application.getNationality());
			appDetails.setStatus(true);
			appList.add(appDetails);
		}
		
		return appList;

	}
	
	@GetMapping(path = "view-application")
	public Application fetchByApplicationId(@RequestParam("applicationId") int appId) {
		return adminService.findByApplicationId(appId);
	}
	
	
	//////Creating of Account by admin -----------CREATE SECTION   
	
	@PostMapping("/account-create-byadmin")
	public Status submitloanDetail(@RequestBody CreateAccountDetailsByAdmin accountDetailsByAdmin) {
		try {
			int applicationId = accountDetailsByAdmin.getApplicationId();
			Application application = adminService.findByApplicationId(applicationId);
			Account account = accountDetailsByAdmin.getAccount();
			account.setApplication(application);
			application.setAccount(account);
			
			application = adminService.updateApplicationByAdmin(application);

			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Account details submitted by Admin successfully");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Error occurred while submitting Account details" + " " + e.getMessage());
			return status;
		}
	}
	
	
}
