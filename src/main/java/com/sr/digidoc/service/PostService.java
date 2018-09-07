package com.sr.digidoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.model.Post;
import com.sr.digidoc.repository.PostRepository;

@Service
@Transactional
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public void save(Post post){
		postRepository.save(post);
	}
	
	public List<Post> getPostByDoctor(Integer doctorId){
		return postRepository.getPostByDoctor(doctorId);
	}
}
