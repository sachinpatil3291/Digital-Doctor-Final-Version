package com.sr.digidoc.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private List<MultipartFile> images;
	private Integer clinicId;
	private Integer doctorId;
	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public Integer getClinicId() {
		return clinicId;
	}

	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	

}
