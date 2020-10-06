package com.lti.dto;

import java.time.LocalDate;

public class LoanDetailsForAdmin {
	private int applicationId;
	private double interestRate;
	private double loanAmount;
	private double maxLoanAmount;
	private int tenure;
	private double emi;
	private double customerMonthlyIncome;
	private String eligibilityStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private String loanStatus;
	private boolean myStatus;
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getMaxLoanAmount() {
		return maxLoanAmount;
	}
	public void setMaxLoanAmount(double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public double getCustomerMonthlyIncome() {
		return customerMonthlyIncome;
	}
	public void setCustomerMonthlyIncome(double customerMonthlyIncome) {
		this.customerMonthlyIncome = customerMonthlyIncome;
	}
	public String getEligibilityStatus() {
		return eligibilityStatus;
	}
	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public boolean isMyStatus() {
		return myStatus;
	}
	public void setMyStatus(boolean myStatus) {
		this.myStatus = myStatus;
	}
	
	
	
	
	

}
