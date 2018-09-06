package com.sr.digidoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Prescription;

@Transactional
@Service
public interface PostRepository extends JpaRepository<Prescription, Integer>{
	
	@Query("SELECT p from Prescription p where p.SFHCCode=:SFHCCode")
	public List<Prescription> getPostByDoctor(@Param("doctorId")String doctorId);
	
}
