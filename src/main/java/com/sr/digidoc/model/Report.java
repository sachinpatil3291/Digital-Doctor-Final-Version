package com.sr.digidoc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="report")
public class Report extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@Column(name="DoctorId")
	private Integer doctorId;
	
	@Column(name="PatientId")
	private Integer patientId;
	
	@Column(name="SFHC")
	private String SFHCCode;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ReportId")
	private List<Image> images;
	
	@Column(name="Date")
	private Date DateAndTime;
	
	@Transient
	private List<MultipartFile> reportImages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getSFHCCode() {
		return SFHCCode;
	}

	public void setSFHCCode(String sFHCCode) {
		SFHCCode = sFHCCode;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Date getDateAndTime() {
		return DateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		DateAndTime = dateAndTime;
	}

	public List<MultipartFile> getReportImages() {
		return reportImages;
	}

	public void setReportImages(List<MultipartFile> reportImages) {
		this.reportImages = reportImages;
	}
}
