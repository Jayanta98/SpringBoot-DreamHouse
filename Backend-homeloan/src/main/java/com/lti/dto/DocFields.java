package com.lti.dto;

public class DocFields extends Status {

	private int documentId;
	private String panCard;
	private String voterIdCard;
	private String salarySlip;
	private String loa;
	private String nocFromBuilder;
	private String agreementToSale;
	private int applicationId;
	
	public int getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	
	public String getPanCard() {
		return panCard;
	}
	
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	
	public String getVoterIdCard() {
		return voterIdCard;
	}
	
	public void setVoterIdCard(String voterIdCard) {
		this.voterIdCard = voterIdCard;
	}
	
	public String getSalarySlip() {
		return salarySlip;
	}
	
	public void setSalarySlip(String salarySlip) {
		this.salarySlip = salarySlip;
	}
	
	public String getLoa() {
		return loa;
	}
	
	public void setLoa(String loa) {
		this.loa = loa;
	}
	
	public String getNocFromBuilder() {
		return nocFromBuilder;
	}
	
	public void setNocFromBuilder(String nocFromBuilder) {
		this.nocFromBuilder = nocFromBuilder;
	}
	
	public String getAgreementToSale() {
		return agreementToSale;
	}
	
	public void setAgreementToSale(String agreementToSale) {
		this.agreementToSale = agreementToSale;
	}
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	
}
