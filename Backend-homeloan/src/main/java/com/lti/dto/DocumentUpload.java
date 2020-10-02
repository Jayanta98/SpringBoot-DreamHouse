package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentUpload {

	private int applicationId;
	private MultipartFile panCard;
	private MultipartFile voterIdCard;
	private MultipartFile salarySlip;
	private MultipartFile loa;
	private MultipartFile nocFromBuilder;
	private MultipartFile agreementToSale;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public MultipartFile getPanCard() {
		return panCard;
	}
	
	public void setPanCard(MultipartFile panCard) {
		this.panCard = panCard;
	}
	
	public MultipartFile getVoterIdCard() {
		return voterIdCard;
	}
	
	public void setVoterIdCard(MultipartFile voterIdCard) {
		this.voterIdCard = voterIdCard;
	}
	
	public MultipartFile getSalarySlip() {
		return salarySlip;
	}
	
	public void setSalarySlip(MultipartFile salarySlip) {
		this.salarySlip = salarySlip;
	}
	
	public MultipartFile getLoa() {
		return loa;
	}
	
	public void setLoa(MultipartFile loa) {
		this.loa = loa;
	}
	
	public MultipartFile getNocFromBuilder() {
		return nocFromBuilder;
	}
	
	public void setNocFromBuilder(MultipartFile nocFromBuilder) {
		this.nocFromBuilder = nocFromBuilder;
	}
	
	public MultipartFile getAgreementToSale() {
		return agreementToSale;
	}
	
	public void setAgreementToSale(MultipartFile agreementToSale) {
		this.agreementToSale = agreementToSale;
	}
	
	
	
	
}
