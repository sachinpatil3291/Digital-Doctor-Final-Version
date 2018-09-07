package com.sr.digidoc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sr.digidoc.model.Patient;
import com.sr.digidoc.repository.PatientRepository;
import com.sr.digidoc.service.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientRepositoryTest {

	@Mock
	PatientRepository patientRepository;
	
	@Mock
	Patient patient;
	
	@Autowired
	PatientService patientService;
	
	@Test(expected=Exception.class)
	public void createPatientTest() {
		Patient p = null;
		doThrow().when(patientRepository).save(p);
		verify(patientService).save(patient);
	}

}
