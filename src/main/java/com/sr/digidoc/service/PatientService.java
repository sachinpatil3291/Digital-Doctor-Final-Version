package com.sr.digidoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Patient;
import com.sr.digidoc.repository.PatientRepository;

@Transactional
@Service("patientService")
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;

	public void save(Patient patient) {
		String SFHC = patient.getFirstName().substring(0, 3) + patient.getPassword().substring(0, 3);
		patient.setSFHCCode(SFHC);
		patientRepository.save(patient);
	}

	public Patient getPatientDetails(String SFHC, String password) {
		Patient patient = null;
		if(patientRepository.findBySFHCAndPassword(SFHC, password).isPresent()){
			return patientRepository.findBySFHCAndPassword(SFHC, password).get();
		}else{
			patient = new Patient();
			patient.setErrorMsg("Record not found");
			patient.setStatusCode(1);
		}
		return patient;
	}

	public Patient findBySFHC(String SFHC) {
		return patientRepository.findBySFHC(SFHC);
	}
}
