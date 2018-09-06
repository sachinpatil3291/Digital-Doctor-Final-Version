package com.sr.digidoc.controller;

import java.util.List;
import java.util.Optional;

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

import com.sr.digidoc.model.BaseResponse;
import com.sr.digidoc.model.Doctor;
import com.sr.digidoc.service.DoctorService;

@RestController
public class DoctorRestController {

	@Autowired
	DoctorService doctorService;

	@RequestMapping(value = "/api/doctor/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> doctorLogin(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		Optional<Doctor> doctor = doctorService.getDocotInfo(email, password);
		doctor.get().setStatusCode(0);
		doctor.get().setSuccessMsg("Doctor info retrived successfully");
		return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/doctor/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> doctorSignup(@RequestBody Doctor doctor) {
		BaseResponse response = new BaseResponse();
		doctorService.save(doctor);
		response.setStatusCode(0);
		response.setSuccessMsg("signUp successfull.");
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/doctors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> getDoctorList() {
		List<Doctor> doctorList = doctorService.findAll();
		return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/doctors/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> updateDoctorProfile(@PathVariable("id")Integer id,@RequestBody Doctor doctor) {
		BaseResponse response = new BaseResponse();
		doctorService.update(doctor);
		response.setStatusCode(0);
		response.setSuccessMsg("Profile updated successfull.");
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
}
