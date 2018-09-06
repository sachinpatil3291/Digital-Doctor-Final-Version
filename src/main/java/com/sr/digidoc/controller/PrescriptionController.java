package com.sr.digidoc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sr.digidoc.model.Prescription;
//import com.sr.digidoc.service.PrescriptionService;
import com.sr.digidoc.service.PrescriptionService;

@Controller
public class PrescriptionController {

	@Autowired
	PrescriptionService prescriptionService;
	
	@RequestMapping(value = "/api/prescriptions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Prescription> savePrescription(@RequestBody Prescription prescription) {
		prescriptionService.saveAndReturn(prescription);
		prescription.setStatusCode(0);
		prescription.setSuccessMsg("Prescription saved successfully.");
		return new ResponseEntity<Prescription>(prescription, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/prescriptions/getbyid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Prescription> getPrescription(@PathVariable("id") Integer id) {
		Optional<Prescription> prescription = prescriptionService.findById(id);
		prescription.get().setStatusCode(0);
		prescription.get().setSuccessMsg("Prescription retrived successfully.");
		return new ResponseEntity<Prescription>(prescription.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/prescriptions/{SFHC}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Prescription>> getPrescriptionBYSFHC(@PathVariable("SFHC") String SFHC){
		List<Prescription> prescriptionList = prescriptionService.findBySFHC(SFHC);
		return new ResponseEntity<List<Prescription>>(prescriptionList, HttpStatus.OK);
	}
}
