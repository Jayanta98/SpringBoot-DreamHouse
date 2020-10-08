package com.lti.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
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
import com.lti.dto.DocFields;
import com.lti.dto.IncomeFields;
import com.lti.dto.LoanDetails;
import com.lti.dto.LoanDetailsForAdmin;
import com.lti.dto.PropertyFields;
import com.lti.dto.RegistrationDetails;
import com.lti.dto.Status;
import com.lti.dto.UpdateApplicationStatusDetail;
import com.lti.entity.Account;
import com.lti.entity.Admin;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;
import com.lti.exception.AdminServiceException;
import com.lti.service.AdminService;
import com.lti.service.ApplicationService;
import com.lti.dto.LoanFields;

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
			appDetails.setApplicationId(application.getApplicationId());
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
	
	
	@GetMapping("/view-all-income-details")
	public List<IncomeFields> viewAllIncomeDetails() {
		
		List<IncomeFields> incomeList = new  ArrayList<IncomeFields>();
		List<Income> list = adminService.getAllIncomeDetails();
		
		for(Income income : list) {
			IncomeFields field = new IncomeFields();
			field.setIncomeId(income.getIncomeId());
			field.setEmployerName(income.getEmployerName());
			field.setOrganizationType(income.getOrganizationType());
			field.setTypeOfEmployement(income.getTypeOfEmployement());
			field.setApplicationId(income.getApplication().getApplicationId());
			field.setStatus(true);
			field.setStatusMessage("Fetching successfull");
			incomeList.add(field);
		}
		
		return incomeList;
	}
	
	@GetMapping("/view-all-property-details")
	public List<PropertyFields> viewAllPropertyDetails() {
		List<PropertyFields> propertyList = new ArrayList<PropertyFields>();
		List<Property> list = adminService.getProperty();
		
		for(Property property : list) {
			PropertyFields field = new PropertyFields();
			field.setPropertyId(property.getPropertyId());
			field.setPropertyLocation(property.getPropertyLocation());
			field.setPropertyName(property.getPropertyName());
			field.setEstimatedAmount(property.getEstimatedAmount());
			field.setApplicationId(property.getApplication().getApplicationId());
			field.setStatus(true);
			field.setStatusMessage("Fetch successfull");
			propertyList.add(field);
		}
		
		return propertyList;
	}
	
	
	@GetMapping("/view-all-loan-details")
	public List<LoanFields> viewAllLoanDetails() {
		List<LoanFields> loanList = new ArrayList<LoanFields>();
		List<Loan> list = adminService.getAllLoanDetails();
		
		for(Loan loan : list) {
			LoanFields field = new LoanFields();
			field.setApplicationId(loan.getApplication().getApplicationId());
			field.setCustomerMonthlyIncome(loan.getCustomerMonthlyIncome());
			field.setEligibilityStatus(loan.getEligibilityStatus());
			field.setEmi(loan.getEmi());
			field.setEndDate(loan.getEndDate());
			field.setInterestRate(loan.getInterestRate());
			field.setLoanAmount(loan.getLoanAmount());
			field.setLoanId(loan.getLoanId());
			field.setLoanStatus(loan.getLoanStatus());
			field.setMaxLoanAmount(loan.getMaxLoanAmount());
			field.setStartDate(loan.getStartDate());
			field.setTenure(loan.getTenure());
			field.setStatusMessage("Fetch successfull");
			field.setStatus(true);
			loanList.add(field);
		}
		
		return loanList;
	}
	
	
	@GetMapping("/view-application")
	public ApplicationDetails fetchAppDetailByApplicationId(@RequestParam("applicationId") int appId) {
		try {
			Application application = adminService.findByApplicationId(appId);
			ApplicationDetails appDetail = new ApplicationDetails();
			appDetail.setAadharNo(application.getAadharNo());
			appDetail.setApplicationId(application.getApplicationId());
			appDetail.setApplicationStatusMessage(application.getApplicationStatus());
			appDetail.setDateOfAppointment(application.getDateOfAppointment());
			appDetail.setDateOfBirth(application.getDateOfBirth());
			appDetail.setEmail(application.getEmail());
			appDetail.setFirstname(application.getFirstname());
			appDetail.setLastname(application.getLastname());
			appDetail.setMiddlename(application.getMiddlename());
			appDetail.setNationality(application.getNationality());
			appDetail.setPanNo(application.getPanNo());
			appDetail.setPhoneNo(application.getPhoneNo());
			appDetail.setStatus(true);
			if(applicationService.isAccountPresent(appId)) {
				Account account = applicationService.fetchAccountByAppId(appId);
				appDetail.setAccountNo(account.getAccountNo());
				appDetail.setAmmount(account.getAmount());
				appDetail.setAccountType(account.getAccountType());
			}
			return appDetail;
		} 
		catch (Exception e) {
			ApplicationDetails appDetail = new ApplicationDetails();
			appDetail.setStatus(false);
			appDetail.setApplicationStatusMessage("Invalid application-Id");
			return appDetail;
		}
	}
	
	@GetMapping("/view-income")
	public IncomeFields fetchIncomeDetailByApplicationId(@RequestParam("applicationId") int appId) {
		try {
			Income income = adminService.incomeDetailsByApplicationId(appId);
			IncomeFields field = new IncomeFields();
			field.setIncomeId(income.getIncomeId());
			field.setEmployerName(income.getEmployerName());
			field.setOrganizationType(income.getOrganizationType());
			field.setTypeOfEmployement(income.getTypeOfEmployement());
			field.setApplicationId(income.getApplication().getApplicationId());
			field.setStatus(true);
			field.setStatusMessage("Fetching successfull");
			return field;
		}
		catch(Exception e) {
			IncomeFields field = new IncomeFields();
			field.setStatus(false);
			field.setStatusMessage("Income data does not exist");
			return field;
		}
	}
	
	@GetMapping("/view-property")
	public PropertyFields fetchPropertyDetailByApplicationId(@RequestParam("applicationId") int appId) {
		try {
			Property property = adminService.propertyDetailByApplicationId(appId);
			PropertyFields field = new PropertyFields();
			field.setPropertyId(property.getPropertyId());
			field.setPropertyLocation(property.getPropertyLocation());
			field.setPropertyName(property.getPropertyName());
			field.setEstimatedAmount(property.getEstimatedAmount());
			field.setApplicationId(property.getApplication().getApplicationId());
			field.setStatus(true);
			field.setStatusMessage("Fetch successfull");
			return field;
		}
		catch(Exception e) {
			PropertyFields field = new PropertyFields();
			field.setStatus(false);
			field.setStatusMessage("Property data does not exist");
			return field;
		}
	}
	
	@GetMapping("/view-loan")
	public LoanFields fetchLoanDetailByApplicationId(@RequestParam("applicationId") int appId) {
		try {
			Loan loan = adminService.loanDetailsByApplicationId(appId);
			LoanFields field = new LoanFields();
			field.setApplicationId(loan.getApplication().getApplicationId());
			field.setCustomerMonthlyIncome(loan.getCustomerMonthlyIncome());
			field.setEligibilityStatus(loan.getEligibilityStatus());
			field.setEmi(loan.getEmi());
			field.setEndDate(loan.getEndDate());
			field.setInterestRate(loan.getInterestRate());
			field.setLoanAmount(loan.getLoanAmount());
			field.setLoanId(loan.getLoanId());
			field.setLoanStatus(loan.getLoanStatus());
			field.setMaxLoanAmount(loan.getMaxLoanAmount());
			field.setStartDate(loan.getStartDate());
			field.setTenure(loan.getTenure());
			field.setStatusMessage("Fetch successfull");
			field.setStatus(true);
			return field;
		}
		catch(Exception e) {
			LoanFields field = new LoanFields();
			field.setStatus(false);
			field.setStatusMessage("Loan data does not exist");
			return field;
		}
	}
	
	@GetMapping("/view-document")
	public DocFields fetchDocDetailByApplicationId(@RequestParam("applicationId") int appId, HttpServletRequest request) {
		try {
			Document doc = adminService.documentDetailsByApplicationId(appId);
			DocFields field = new DocFields();
			field.setDocumentId(doc.getDocumentId());
			field.setAgreementToSale(doc.getAgreementToSale());
			field.setLoa(doc.getLoa());
			field.setNocFromBuilder(doc.getNocFromBuilder());
			field.setPanCard(doc.getPanCard());
			field.setSalarySlip(doc.getSalarySlip());
			field.setVoterIdCard(doc.getVoterIdCard());
			field.setApplicationId(doc.getApplication().getApplicationId());
			
			//reading the project's deployed folder projection
			String projPath = request.getServletContext().getRealPath("/");
			String tempDownloadPath = projPath + "/download/";
			//creating a folder within the project where we will place the profile pic of the customer getting fetched
			File f = new File(tempDownloadPath);
			if(!f.exists()) {
				f.mkdir();
			}
			String targetFilePan = tempDownloadPath + doc.getPanCard();
			String targetFileVoter = tempDownloadPath + doc.getVoterIdCard();
			String targetFileSalary = tempDownloadPath + doc.getSalarySlip();
			String targetFileLoa = tempDownloadPath + doc.getLoa();
			String targetFileNoc = tempDownloadPath + doc.getNocFromBuilder();
			String targetFileSaleAgreement = tempDownloadPath + doc.getAgreementToSale();
			
			String imageUploadLocation = "F:/docs/";
			String sourceFilePan = imageUploadLocation + doc.getPanCard();
			String sourceFileVoter = imageUploadLocation + doc.getVoterIdCard();
			String sourceFileSalary = imageUploadLocation + doc.getSalarySlip();
			String sourceFileLoa = imageUploadLocation + doc.getLoa();
			String sourceFileNoc = imageUploadLocation + doc.getNocFromBuilder();
			String sourceFileSaleAgreement = imageUploadLocation + doc.getAgreementToSale();
			
			try {
				FileCopyUtils.copy(new File(sourceFilePan), new File(targetFilePan));
				FileCopyUtils.copy(new File(sourceFileVoter), new File(targetFileVoter));
				FileCopyUtils.copy(new File(sourceFileSalary), new File(targetFileSalary));
				FileCopyUtils.copy(new File(sourceFileLoa), new File(targetFileLoa));
				FileCopyUtils.copy(new File(sourceFileNoc), new File(targetFileNoc));
				FileCopyUtils.copy(new File(sourceFileSaleAgreement), new File(targetFileSaleAgreement));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			field.setStatus(true);
			field.setStatusMessage("Fetch successfull");
			return field;
		} 
		catch (Exception e) {
			DocFields field = new DocFields();
			field.setStatus(false);
			field.setStatusMessage("Documents not found");
			return field;
		}
		
	}
	
	//////Creating of Account by admin -----------CREATE SECTION   
	
	@PostMapping("/account-create-byadmin")
	public Status submitloanDetail(@RequestBody CreateAccountDetailsByAdmin accountDetailsByAdmin) {
		try {
			int applicationId = accountDetailsByAdmin.getApplicationId();
			Application application = adminService.findByApplicationId(applicationId);
			Account account = accountDetailsByAdmin.getAccount();
			account.setApplication(application);
			if(applicationService.isAccountPresent(applicationId)) {
				Account oldAccount = applicationService.fetchAccountByAppId(applicationId);
				oldAccount.setAmount(account.getAmount());
				oldAccount.setAccountType(account.getAccountType());
				application.setAccount(oldAccount);
				application = applicationService.updateApplication(application);
			}
			else {
				account.setApplication(application);
				application.setAccount(account);
				application = applicationService.updateApplication(application);
			}

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
	
	//Update Application Status BY admin (Apply/Approved/Rejected)
	@PostMapping("/update-appStatus")
	public Status updateApplicationStatusBYAdmin(@RequestBody UpdateApplicationStatusDetail updateDetails) {
		try{
			int applicationId=updateDetails.getApplicationId();
			Application application = adminService.findByApplicationId(applicationId);
			application.setApplicationStatus(updateDetails.getNewStatus());
		
			application = adminService.updateApplicationByAdmin(application);
		
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Application Status Updated by Admin successfully");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Error occurred while Updating Application Status" + " " + e.getMessage());
			return status;
		}
		
	}
	
	
	
	//Loading LoanTable Data by applicationId for admin
	@GetMapping("/loanDetailsforAdmin")
	public LoanDetailsForAdmin giveLoanDetails(@RequestParam("applicationId") int applicationId) {
		try {
		
			Loan loan = adminService.loanDetailsByApplicationId(applicationId);
			
			LoanDetailsForAdmin loanDetailsforAdmin= new LoanDetailsForAdmin();
			
			loanDetailsforAdmin.setApplicationId(applicationId);
			loanDetailsforAdmin.setInterestRate(loan.getInterestRate());
			loanDetailsforAdmin.setLoanAmount(loan.getLoanAmount());
			loanDetailsforAdmin.setMaxLoanAmount(loan.getMaxLoanAmount());
			loanDetailsforAdmin.setTenure(loan.getTenure());
			loanDetailsforAdmin.setEmi(loan.getEmi());
			loanDetailsforAdmin.setCustomerMonthlyIncome(loan.getCustomerMonthlyIncome());
			loanDetailsforAdmin.setEligibilityStatus(loan.getEligibilityStatus());
			loanDetailsforAdmin.setStartDate(loan.getStartDate());
			loanDetailsforAdmin.setEndDate(loan.getEndDate());
			loanDetailsforAdmin.setLoanStatus(loan.getLoanStatus());
			loanDetailsforAdmin.setMyStatus(true);
			loanDetailsforAdmin.setMessage("Loan Details Fetched Successfully" );
			
			return loanDetailsforAdmin;
		}
		catch (Exception e) {
			LoanDetailsForAdmin loanDetailsforAdmin= new LoanDetailsForAdmin();
			loanDetailsforAdmin.setMyStatus(false);
			loanDetailsforAdmin.setMessage("Error while fetching Loan Details "+ " " + e.getMessage());
			return loanDetailsforAdmin;
		}
		
	}
	
	
	
	@PostMapping("/update-loanByadmin")
	public Status updateLoanTableByAdmin(@RequestBody LoanDetailsForAdmin loanDetails ) {
		
		try {
				int appId= loanDetails.getApplicationId();
				Application application = adminService.findByApplicationId(appId);
				
				Loan loan = adminService.loanDetailsByApplicationId(appId);
				
				loan.setInterestRate(loanDetails.getInterestRate());
				loan.setLoanAmount(loanDetails.getLoanAmount());
				loan.setMaxLoanAmount(loanDetails.getMaxLoanAmount());
				loan.setTenure(loanDetails.getTenure());
				loan.setEmi(loanDetails.getEmi());
				loan.setCustomerMonthlyIncome(loanDetails.getCustomerMonthlyIncome());
				loan.setEligibilityStatus(loanDetails.getEligibilityStatus());
				loan.setStartDate(loanDetails.getStartDate());
				loan.setEndDate(loanDetails.getEndDate());
				loan.setLoanStatus(loanDetails.getLoanStatus());
				
				loan.setApplication(application);
				application.setLoan(loan);
				application = adminService.updateApplicationByAdmin(application);
				
				Status status = new Status();
				status.setStatus(true);
				status.setStatusMessage("Loan Table Updated by Admin successfully");
				return status;
				
			}catch (Exception e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Error occurred while Updating Loan Table" + " " + e.getMessage());
			return status;
		}
			
		
	}
	
	@GetMapping("/regiDetailsForEditing")
	public RegistrationDetails getRegistrationDetailsForEdit(@RequestParam("applicationId") int applicationId) {
		
		try {
			Application application = adminService.findByApplicationId(applicationId);
			
			RegistrationDetails registerDetails = new RegistrationDetails();
			registerDetails.setApplicationId(applicationId);
			registerDetails.setFirstName(application.getFirstname());
			registerDetails.setMiddleName(application.getMiddlename());
			registerDetails.setLastName(application.getLastname());
			registerDetails.setEmail(application.getEmail());
			registerDetails.setPassword(application.getPassword());
			registerDetails.setPhoneNo(application.getPhoneNo());
			registerDetails.setDateOfBirth(application.getDateOfBirth());
			registerDetails.setNationality(application.getNationality());
			registerDetails.setGender(application.getGender());
			registerDetails.setAadharNo(application.getAadharNo());
			registerDetails.setPanNo(application.getPanNo());
			registerDetails.setStatus(true);
			registerDetails.setStatusMessage("Successfully Fetched All Details of Registration");
			return registerDetails;
		}
		catch (Exception e) {
			RegistrationDetails registerDetails = new RegistrationDetails();
			registerDetails.setStatus(false);
			registerDetails.setStatusMessage("Error while fetching All Details of Registration"+" "+e.getMessage());
			return registerDetails;
			
		}
		
		
	}
	
	
	@PostMapping("/editRegistrationByUser")
	public Status editRegistrationByUser(@RequestBody RegistrationDetails regiDetails) {
		
		try {
			int appId=regiDetails.getApplicationId();
			Application application = adminService.findByApplicationId(appId);
			
			application.setFirstname(regiDetails.getFirstName());
			application.setMiddlename(regiDetails.getMiddleName());
			application.setLastname(regiDetails.getLastName());
			application.setEmail(regiDetails.getEmail());
			application.setPassword(regiDetails.getPassword());
			application.setPhoneNo(regiDetails.getPhoneNo());
			application.setDateOfBirth(regiDetails.getDateOfBirth());
			application.setGender(regiDetails.getGender());
			application.setNationality(regiDetails.getNationality());
			application.setAadharNo(regiDetails.getAadharNo());
			application.setPanNo(regiDetails.getPanNo());
			
			application = adminService.updateApplicationByAdmin(application);
			
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Successfully Edited");
			
			return status;
			
		}
		catch (Exception e) {
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Failed to edit due to : "+" "+e.getMessage());
			
			return status;
		}
		
	}
	

	
	
	
}
