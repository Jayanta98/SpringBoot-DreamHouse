package com.lti.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ApplicationSubmitStatus;
import com.lti.dto.IncomeDetails;
import com.lti.dto.PropertyDetails;
import com.lti.dto.Status;
import com.lti.entity.Application;
import com.lti.entity.Income;
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
	
}
