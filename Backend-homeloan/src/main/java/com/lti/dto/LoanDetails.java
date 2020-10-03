package com.lti.dto;

import com.lti.entity.Loan;

public class LoanDetails {

	private int applicationId;
	private Loan loan;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public Loan getLoan() {
		return loan;
	}
	
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	
}
