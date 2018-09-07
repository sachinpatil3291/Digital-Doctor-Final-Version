package com.sr.digidoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sr.digidoc.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	
	@Query("SELECT r from Report r where r.SFHCCode=:SFHCCode")
	public List<Report> findBySFHC(@Param("SFHC")String sfhcCode);
}
