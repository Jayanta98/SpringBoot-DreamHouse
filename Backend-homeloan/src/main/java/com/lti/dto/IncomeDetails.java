package com.lti.dto;

import com.lti.entity.Income;

public class IncomeDetails {

	private int applicationId;
	private Income income;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public Income getIncome() {
		return income;
	}
	
	public void setIncome(Income income) {
		this.income = income;
	}
	
	
}
