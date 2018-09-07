package com.sr.digidoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Post;

@Transactional
@Service
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	@Query("SELECT p from Post p where p.doctorId=:doctorId")
	public List<Post> getPostByDoctor(@Param("doctorId")Integer doctorId);
	
}
