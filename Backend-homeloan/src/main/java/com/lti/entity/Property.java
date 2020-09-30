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
@Table(name="tbl_property_details")
public class Property {
	
	@Id
	@Column(name = "property_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propertyseq")
	@SequenceGenerator(name = "propertyseq", sequenceName = "property_seq", initialValue = 1000, allocationSize = 1)
	private int propertyId;
	
	@Column(name = "property_location")
	private String propertyLocation;
	
	@Column(name = "property_name")
	private String propertyName;
	
	@Column(name = "estimated_amount")
	private double estimatedAmount;
	
	@OneToOne
	@JoinColumn(name="application_id")
	private Application application;

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(String propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	

}

