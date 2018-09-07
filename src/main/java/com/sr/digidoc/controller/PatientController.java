package com.sr.digidoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sr.digidoc.model.Patient;
import com.sr.digidoc.model.PatientResponse;
import com.sr.digidoc.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/api/patient/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientResponse> patientSignUp(@RequestBody Patient patient) {
		patientService.save(patient);
		PatientResponse response = new PatientResponse();
		response.setSFHC(patient.getSFHCCode());
		response.setStatusCode(0);
		response.setSuccessMsg("Sign Up successfull");
		return new ResponseEntity<PatientResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/patient/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> patientLogin(@RequestParam("sfhcCode") String uniqueCode,
			@RequestParam("password") String password) {
		Patient patient = patientService.getPatientDetails(uniqueCode, password);
		patient.setStatusCode(0);
		patient.setSuccessMsg("Login successfull.");
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/patients/{SFHC}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> getPatient(@PathVariable("SFHC") String uniqueCode) {
		Patient p = patientService.findBySFHC(uniqueCode);
		p.setStatusCode(0);
		p.setSuccessMsg("Patient retrived successfully.");
		return new ResponseEntity<Patient>(p, HttpStatus.OK);
	}
}
