package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_application")
public class Application {
	
	@Id
	@Column(name = "application_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicationseq")
	@SequenceGenerator(name = "applicationseq", sequenceName = "application_seq", initialValue = 10020, allocationSize = 1)
	private int applicationId;
	
	@Column(name = "first_name")
	private String firstname;
	
	@Column(name = "middle_name")
	private String middlename;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	private String phoneNo;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	private String gender;
	
	private String nationality;
	
	@Column(name = "aadhar_no")
	private String aadharNo;
	
	@Column(name = "pan_no")
	private String panNo;
	
	@Column(name = "application_status")
	private String applicationStatus;
	
	@Column(name = "date_of_appointment")
	private LocalDate dateOfAppointment;
	
	@OneToOne(mappedBy="application",cascade = CascadeType.MERGE)
	private Property property;
	
	@OneToOne(mappedBy="application",cascade = CascadeType.MERGE)
	private Income income;
	
	@OneToOne(mappedBy="application",cascade = CascadeType.MERGE)
	private Loan loan;

	@OneToOne(mappedBy="application",cascade = CascadeType.MERGE)
	private Document document;
	
	@OneToOne(mappedBy="application",cascade = CascadeType.MERGE)
	private Account account;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public LocalDate getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(LocalDate dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	

	
	

}
