package com.lti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AccountDetail;
import com.lti.dto.ApplicationDetails;
import com.lti.dto.ApplicationSubmitStatus;
import com.lti.dto.DocumentUpload;
import com.lti.dto.FormStatus;
import com.lti.dto.IncomeDetails;
import com.lti.dto.LoanDetails;
import com.lti.dto.PropertyDetails;
import com.lti.dto.Status;
import com.lti.dto.UserLogin;
import com.lti.dto.UserLoginStatus;
import com.lti.entity.Account;
import com.lti.entity.Application;
import com.lti.entity.Document;
import com.lti.entity.Income;
import com.lti.entity.Loan;
import com.lti.entity.Property;
import com.lti.exception.ApplicationServiceException;
import com.lti.service.ApplicationService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private ApplicationService applicationService;

	@GetMapping("/track")
	public Status trackApplicationStatus(@RequestParam("applicationId") int appId) {
		try {
			String message = applicationService.trackApplicationStatus(appId);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage(message);
			return status;
		}
		catch(ApplicationServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/application-submit")
	public ApplicationSubmitStatus submitApplication(@RequestBody Application application) {
		try {
			
			//Adding two attributes dateOfAppointment and ApplicationStatus
			application.setDateOfAppointment(LocalDate.now().plusDays(7));
			application.setApplicationStatus("Pending");
			
			int appId = applicationService.registerApplication(application);
			
			ApplicationSubmitStatus status = new ApplicationSubmitStatus();
			status.setStatus(true);
			status.setStatusMessage("Application submitted successfully");
			status.setApplicationId(appId);
			status.setName(application.getFirstname());
			status.setDateOfAppointment(application.getDateOfAppointment());
			return status;
		}
		catch(ApplicationServiceException e) {
			ApplicationSubmitStatus status = new ApplicationSubmitStatus();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/income-submit")
	public Status submitIncomeDetails(@RequestBody IncomeDetails incomeDetails) {
		try {
			int applicationId = incomeDetails.getApplicationId();
			Income income = incomeDetails.getIncome();
			Application application = applicationService.findById(applicationId);
			income.setApplication(application);
			application.setIncome(income);
			application = applicationService.updateApplication(application);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Income details submitted successfully");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Error occurred while submitting income details" + " " + e.getMessage());
			return status;
		}
		
	}
	
	@PostMapping("/property-submit")
	public Status submitPropertyDetails(@RequestBody PropertyDetails propertyDetails) {
		try {
			int applicationId = propertyDetails.getApplicationId();
			Property property = propertyDetails.getProperty();
			Application application = applicationService.findById(applicationId);
			property.setApplication(application);
			application.setProperty(property);
			application = applicationService.updateApplication(application);
			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Property details submitted successfully");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Error occurred while submitting property details" + " " + e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/documents-submit")
	public Status documentSubmit(DocumentUpload documentUpload) {
		String imageUploadLocation = "F:/docs/";
		
		String fileNamePan = documentUpload.getPanCard().getOriginalFilename();
		String targetFilePan = imageUploadLocation + fileNamePan;
		String fileNameVoter = documentUpload.getVoterIdCard().getOriginalFilename();
		String targetFileVoter = imageUploadLocation + fileNameVoter;
		String fileNameSalary = documentUpload.getSalarySlip().getOriginalFilename();
		String targetFileSalary = imageUploadLocation + fileNameSalary;
		String fileNameLoa = documentUpload.getLoa().getOriginalFilename();
		String targetFileLoa = imageUploadLocation + fileNameLoa;
		String fileNameNoc = documentUpload.getNocFromBuilder().getOriginalFilename();
		String targetFileNoc = imageUploadLocation + fileNameNoc;
		String fileNameSaleAgreement = documentUpload.getAgreementToSale().getOriginalFilename();
		String targetFileSaleAgreement = imageUploadLocation + fileNameSaleAgreement;
		
		try {
			FileCopyUtils.copy(documentUpload.getPanCard().getInputStream(), new FileOutputStream(targetFilePan));
			FileCopyUtils.copy(documentUpload.getVoterIdCard().getInputStream(), new FileOutputStream(targetFileVoter));
			FileCopyUtils.copy(documentUpload.getSalarySlip().getInputStream(), new FileOutputStream(targetFileSalary));
			FileCopyUtils.copy(documentUpload.getLoa().getInputStream(), new FileOutputStream(targetFileLoa));
			FileCopyUtils.copy(documentUpload.getNocFromBuilder().getInputStream(), new FileOutputStream(targetFileNoc));
			FileCopyUtils.copy(documentUpload.getAgreementToSale().getInputStream(), new FileOutputStream(targetFileSaleAgreement));
		}
		catch(IOException e) {
			e.printStackTrace();
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Documents upload failed");
			return status;
		}
		Application application = applicationService.findById(documentUpload.getApplicationId());
		
		Document document = new Document();
		document.setPanCard(fileNamePan);
		document.setVoterIdCard(fileNameVoter);
		document.setSalarySlip(fileNameSalary);
		document.setLoa(fileNameLoa);
		document.setNocFromBuilder(fileNameNoc);
		document.setAgreementToSale(fileNameSaleAgreement);
		document.setApplication(application);
		
		application.setDocument(document);
		application = applicationService.updateApplication(application);
		
		Status status = new Status();
		status.setStatus(true);
		status.setStatusMessage("Documents uploaded successfully");
		return status;
	}
	
	@PostMapping("/loan-submit")
	public Status submitloanDetail(@RequestBody LoanDetails loanDetails) {
		try {
			int applicationId = loanDetails.getApplicationId();
			Application application = applicationService.findById(applicationId);

			Loan loan = loanDetails.getLoan();
			loan.setInterestRate(8.5);
			loan.setEligibilityStatus(applicationService.elligibilityStatusForLoan(loan.getCustomerMonthlyIncome(), loan.getTenure(), loan.getLoanAmount()));
			loan.setApplication(application);
			loan.setLoanStatus("Apply");//added status
			application.setLoan(loan);
			application = applicationService.updateApplication(application);

			Status status = new Status();
			status.setStatus(true);
			status.setStatusMessage("Loan details submitted successfully");
			return status;
		}
		catch(Exception e) {
			Status status = new Status();
			status.setStatus(false);
			status.setStatusMessage("Error occurred while submitting loan details" + " " + e.getMessage());
			return status;
		}
	}

	@PostMapping("/user-login")
	public UserLoginStatus userLogin(@RequestBody UserLogin userLogin) {
		try {
			Application application = applicationService.applicationLogin(userLogin.getEmail(), userLogin.getPassword());
			UserLoginStatus userLoginStatus = new UserLoginStatus();
			userLoginStatus.setStatus(true);
			userLoginStatus.setApplicationId(application.getApplicationId());
			userLoginStatus.setName(application.getFirstname() + " " + application.getLastname());
			userLoginStatus.setStatusMessage("User Login successfull");
			userLoginStatus.setApplicationStatus(application.getApplicationStatus());
			return userLoginStatus;
		}
		catch(ApplicationServiceException e) {
			UserLoginStatus userLoginStatus = new UserLoginStatus();
			userLoginStatus.setStatus(false);
			userLoginStatus.setStatusMessage(e.getMessage());
			return userLoginStatus;
		}
	}
	

	@GetMapping("/applicationdetails")
	public ApplicationDetails getApplicationDetails(@RequestParam("applicationId") int appId) {
		try {
			Application application = applicationService.findById(appId);
			ApplicationDetails appDetails = new ApplicationDetails();
			if(applicationService.isAccountPresent(appId)) {
				Account account = applicationService.fetchAccountByAppId(appId);
				appDetails.setAccountNo(account.getAccountNo());
				appDetails.setAmmount(account.getAmount());
				appDetails.setAccountType(account.getAccountType());
			}
			appDetails.setFirstname(application.getFirstname());
			appDetails.setMiddlename(application.getMiddlename());
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
			return appDetails;
		}
		catch(Exception e) {
			ApplicationDetails appDetails = new ApplicationDetails();
			appDetails.setStatus(false);
			appDetails.setApplicationStatusMessage("Invalid application-Id");
			return appDetails;
		}
	}
	
	@GetMapping("/accountdetails")
	public AccountDetail getAccountDetails(@RequestParam("accountNo") int accno) {
		try {
			Account account = applicationService.findAccountByAccountNo(accno);
			AccountDetail accountdetail = new AccountDetail();
			accountdetail.setFirstName(account.getFirstName());
			accountdetail.setLastName(account.getLastName());
			accountdetail.setAccountType(account.getAccountType());
			accountdetail.setBranchCode(account.getBranchCode());
			accountdetail.setBranchName(account.getBranchName());
			accountdetail.setAccountStatus(true);
			accountdetail.setIfscCode(account.getIfscCode());
			accountdetail.setStatusMessage("Account details fetched");
			return accountdetail;
		}
		catch(Exception e) {
			AccountDetail accountdetail = new AccountDetail();
			accountdetail.setAccountStatus(false);
			accountdetail.setStatusMessage(e.getMessage());
			return accountdetail;
		}
	}
	
	
	@GetMapping("/trackForm")
	public FormStatus getNextFormToBeFilled(@RequestParam("applicationId") int applicationId) {
		try {
			String message = applicationService.trackApplicationStatus(applicationId);
			FormStatus formStatus = new FormStatus();
			if(!applicationService.isIncomeFormFilled(applicationId)) {
				formStatus.setCurrentForm(1);
				formStatus.setStatusMessage("On income form");
			}
			else if(!applicationService.isPropertyFormFilled(applicationId)) {
				formStatus.setCurrentForm(2);
				formStatus.setStatusMessage("On property form");
			}
			else if(!applicationService.isLoanFormFilled(applicationId)) {
				formStatus.setCurrentForm(3);
				formStatus.setStatusMessage("On loan form");
			}
			else if(!applicationService.isDocumentFormFilled(applicationId)) {
				formStatus.setCurrentForm(4);
				formStatus.setStatusMessage("On doc form");
			}
			else {
				formStatus.setCurrentForm(0);
				formStatus.setStatusMessage("All forms filled");
			}
			formStatus.setApplicationId(applicationId);
			formStatus.setStatus(true);
			return formStatus;
		} 
		catch (ApplicationServiceException e) {
			FormStatus formStatus = new FormStatus();
			formStatus.setApplicationId(applicationId);
			formStatus.setStatus(false);
			formStatus.setCurrentForm(-1);
			formStatus.setStatusMessage(e.getMessage());
			return formStatus;
		}
		
	}
	
}
