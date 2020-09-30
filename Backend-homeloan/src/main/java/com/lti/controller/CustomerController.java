package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
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
}
