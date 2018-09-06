package com.sr.digidoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Prescription;
import com.sr.digidoc.repository.PrescriptionRepository;

@Transactional
@Service
public class PrescriptionService {

	@Autowired
	PrescriptionRepository prescriptionRepository;

	public Optional<Prescription> saveAndReturn(Prescription prescription) {
		prescriptionRepository.save(prescription);
		return prescriptionRepository.findById(prescription.getId());
	}

	public Optional<Prescription> findById(Integer id) {
		return prescriptionRepository.findById(id);
	}

	public List<Prescription> findBySFHC(String SFHC) {
		List<Prescription> prescriptionList = prescriptionRepository.findBySFHC(SFHC);
		prescriptionList.forEach((prescription)->{
			prescription.setStatusCode(0);
			prescription.setSuccessMsg("Prescription retrived successfully");
		});
		return prescriptionList;
	}
	
	
}
