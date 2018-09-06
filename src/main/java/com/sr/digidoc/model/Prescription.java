package com.sr.digidoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sr.digidoc.util.JsonDateSerializer;

@JsonAutoDetect
@Entity
@Table(name="prescription")
public class Prescription extends BaseResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="SFHC")
	private String SFHCCode;
	
	@Column(name="PatientId")
	private Integer patientId;
	
	@Column(name="DoctorId")
	private Integer doctorId;
	
	@Column(name="DoctorName")
	private String doctorName;
	
	@Column(name="IllnessType")
	private String illnessType;
	
	@Column(name="MedProvided")
	private String medicineProvided;
	
	@Column(name="MedAdvised")
	private String medicineAdvised;
	
	@Column(name="Date")
	private java.util.Date dateAndTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSFHCCode() {
		return SFHCCode;
	}

	public void setSFHCCode(String sFHCCode) {
		SFHCCode = sFHCCode;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getIllnessType() {
		return illnessType;
	}

	public void setIllnessType(String illnessType) {
		this.illnessType = illnessType;
	}

	public String getMedicineProvided() {
		return medicineProvided;
	}

	public void setMedicineProvided(String medicineProvided) {
		this.medicineProvided = medicineProvided;
	}

	public String getMedicineAdvised() {
		return medicineAdvised;
	}

	public void setMedicineAdvised(String medicineAdvised) {
		this.medicineAdvised = medicineAdvised;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
}
