package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminLogin;
import com.lti.dto.AdminLoginStatus;
import com.lti.entity.Admin;
import com.lti.entity.Application;
import com.lti.exception.AdminServiceException;
import com.lti.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
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
	public List<Application> viewAllApplications() {
		List<Application> list = adminService.showAllApplications();
		return list;
	}
	
	@GetMapping(path = "view-application")
	public Application fetchByApplicationId(@RequestParam("applicationId") int appId) {
		return adminService.findByApplicationId(appId);
	}
	
	
	
}
