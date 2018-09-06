package com.sr.digidoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sr.digidoc.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
	
	@Query("SELECT c from Clinic c where name=:name")
	public Clinic findByName(@Param("name")String name);
	
	@Modifying
	@Query("UPDATE Clinic set imageURL=:imageURL where id=:id")
	public int updateClinicImage(@Param("imageURL") String imageURL,
			@Param("id")Integer id);
}
