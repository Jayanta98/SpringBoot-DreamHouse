package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_loan_details")
public class Loan {
	
	@Id
	@Column(name = "loan_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanseq")
	@SequenceGenerator(name = "loanseq", sequenceName = "loan_seq", initialValue = 1000, allocationSize = 1)
	private int loanId;
	
	@Column(name = "max_loan_amount")//This is filled By Admin how much homeLoan provided by bank
	private double maxLoanAmount;
	
	@Column(name = "interest_rate")
	private double interestRate;
	
	@Column(name = "loan_amount")
	private double loanAmount;
	
	private int tenure;
	
	private double emi;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "loan_status")
	private String loanStatus;
	
	@Column(name="customer_monthly_income")
	private double customerMonthlyIncome;
	
	@Column(name="elligibility_status")
	private String eligibilityStatus;
	
	@OneToOne
	@JoinColumn(name= "application_id")
	private Application application;
	
	
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

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	

}

