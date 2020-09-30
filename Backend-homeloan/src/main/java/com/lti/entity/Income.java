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
@Table(name="tbl_income_details")
public class Income {
	
	@Id
	@Column(name = "income_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incomeseq")
	@SequenceGenerator(name = "incomeseq", sequenceName = "income_seq", initialValue = 1000, allocationSize = 1)
	private int incomeId;

	@Column(name = "type_of_employement")
	private String typeOfEmployement;
	
	@Column(name = "retirement_age")
	private int retirementAge;
	
	@Column(name = "organization_type")
	private String organizationType;
	
	@Column(name = "employer_name")
	private String employerName;
	
	@OneToOne
	@JoinColumn(name="application_id")
	private Application application;

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	public String getTypeOfEmployement() {
		return typeOfEmployement;
	}

	public void setTypeOfEmployement(String typeOfEmployement) {
		this.typeOfEmployement = typeOfEmployement;
	}

	public int getRetirementAge() {
		return retirementAge;
	}

	public void setRetirementAge(int retirementAge) {
		this.retirementAge = retirementAge;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	
}

