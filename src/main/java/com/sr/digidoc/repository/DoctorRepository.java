package com.sr.digidoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sr.digidoc.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	@Modifying
	@Query("UPDATE Doctor set photo=:imageURL where id=:id")
	public int updateDoctorImage(@Param("imageURL") String imageURL,
			@Param("id")Integer id);
	
	@Query("SELECT d from Doctor d where email=:email and password=:password")
	public Optional<Doctor> findByEmailAndPassword(@Param("email")String email,
			@Param("password")String password);
}
