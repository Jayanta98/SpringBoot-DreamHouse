package com.lti.entity;

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
@Table(name="tbl_document_details")
public class Document {
	
	@Id
	@Column(name = "document_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docseq")
	@SequenceGenerator(name = "docseq", sequenceName = "doc_seq", initialValue = 1000, allocationSize = 1)
	private int documentId;
	
	@Column(name = "pan_card")
	private String panCard;
	
	@Column(name = "voter_id_card")
	private String voterIdCard;
	
	@Column(name = "salary_slip")
	private String salarySlip;
	
	private String loa;
	
	@Column(name = "noc_from_builder")
	private String nocFromBuilder;
	
	@Column(name = "agreement_to_sale")
	private String agreementToSale;
	
	@OneToOne
	@JoinColumn(name="application_id")
	private Application application;

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

	public String getSalarySlip() {
		return salarySlip;
	}

	public void setSalarySlip(String salarySlip) {
		this.salarySlip = salarySlip;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
		
}
