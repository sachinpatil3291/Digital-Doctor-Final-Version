package com.sr.digidoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Patient;

@Transactional
@Service
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	@Query("SELECT p from Patient p where p.SFHC=:SFHC")
	public Patient findBySFHC(@Param("SFHC")String sfhcCode);
	
	@Query("SELECT p from Patient p where p.SFHC=:SFHC and p.password=:password")
	public Optional<Patient> findBySFHCAndPassword(@Param("SFHC")String sfhcCode,
			@Param("password")String password);
}
