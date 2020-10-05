package com.lti.dto;

import java.time.LocalDate;


public class ApplicationDetails {
	
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNo;
	private LocalDate dateOfBirth;
	private String nationality;
	private String aadharNo;
	private String panNo;
	private LocalDate dateOfAppointment;
	private String applicationStatusMessage;
	private boolean status;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getAadharNo() {
		return aadharNo;
	}
	
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	
	public String getPanNo() {
		return panNo;
	}
	
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	
	public LocalDate getDateOfAppointment() {
		return dateOfAppointment;
	}
	
	public void setDateOfAppointment(LocalDate dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}
	
	public String getApplicationStatusMessage() {
		return applicationStatusMessage;
	}
	
	public void setApplicationStatusMessage(String applicationStatusMessage) {
		this.applicationStatusMessage = applicationStatusMessage;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}