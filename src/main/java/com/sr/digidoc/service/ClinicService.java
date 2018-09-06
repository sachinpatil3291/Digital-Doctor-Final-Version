package com.sr.digidoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Clinic;
import com.sr.digidoc.repository.ClinicRepository;

@Transactional
@Service
public class ClinicService {

	@Autowired
	ClinicRepository clinicRepository;
	
	public Optional<Clinic> findById(Integer id) {
		return clinicRepository.findById(id);
	}
	
	public List<Clinic> findAll() {
		return clinicRepository.findAll();
	}
	
	public void save(Clinic clinic) {
		clinicRepository.save(clinic);
	}
	
	public void update(Clinic clinic){
		Clinic c = clinicRepository.getOne(clinic.getId());
		c.setAddress(clinic.getAddress());
		c.setContact(clinic.getContact());
		c.setId(clinic.getId());
		c.setImages(clinic.getImages());
		c.setImageURL(clinic.getImageURL());
		c.setName(clinic.getName());
		clinicRepository.save(c);
	}
	
	public void delete(int id ) {
		clinicRepository.deleteById(id);
	}
	
	public List<String> getClinicNames() {
		List<String> clinicList = new ArrayList<String>();
		for(Clinic c : clinicRepository.findAll()){
			clinicList.add(c.getName());
		}
		return clinicList;
	}
	
	public Integer getClinicId(String name) {
		return clinicRepository.findByName(name).getId();
	}
	
	public int updateClinicImage(String imageURL,Integer id) {
		return clinicRepository.updateClinicImage(imageURL, id);
	}
}