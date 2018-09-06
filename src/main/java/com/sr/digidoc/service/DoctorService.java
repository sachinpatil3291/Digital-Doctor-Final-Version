package com.sr.digidoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Doctor;
import com.sr.digidoc.repository.ClinicRepository;
import com.sr.digidoc.repository.DoctorRepository;

@Transactional
@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ClinicRepository clinicRepository;
	
	public Optional<Doctor> findById(Integer id) {
		return doctorRepository.findById(id);
	}
	
	public List<Doctor> findAll() {
		List<Doctor> doctorList = doctorRepository.findAll();
		doctorList.forEach((doctor)->{
			doctor.setClinicName(clinicRepository.findById(doctor.getClinicId()).get().getName());
		});
		return doctorList;
	}
	
	public void save(Doctor doc) {
		doctorRepository.save(doc);
	}
	
	public void update(Doctor doctor){
		Doctor d = populateDoctorObj(doctor);
		doctorRepository.save(d);
	}
	public void delete(int id) {
		doctorRepository.deleteById(id);
	}
	
	public int updateDoctorImage(String imageURL,Integer id) {
		return doctorRepository.updateDoctorImage(imageURL, id);
	}

	public Optional<Doctor> getDocotInfo(String email, String password) {
		Optional<Doctor> doctor = Optional.empty();
		if(doctorRepository.findByEmailAndPassword(email,password).isPresent()){
			return doctorRepository.findByEmailAndPassword(email,password);
		}else{
			doctor.get().setStatusCode(1);
			doctor.get().setErrorMsg("Record not found..!");
		}
		return doctor;
	}
	Doctor populateDoctorObj(Doctor d){
		Doctor doc = doctorRepository.getOne(d.getId());
		doc.setId(d.getId());
		doc.setAwards(d.getAwards());
		doc.setClinicId(d.getClinicId());
		doc.setClinicName(d.getClinicName());
		doc.setContact(d.getContact());
		doc.setDegree(d.getDegree());
		doc.setEmail(d.getEmail());
		doc.setName(d.getName());
		doc.setPassword(d.getPassword());
		doc.setPhoto(d.getPhoto());
		doc.setSpecialist(d.getSpecialist());
		return doc;
	}
}