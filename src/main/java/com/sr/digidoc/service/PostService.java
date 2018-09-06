package com.sr.digidoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sr.digidoc.repository.PostRepository;

@Service
@Transactional
public class PostService {

	@Autowired
	PostRepository postRepository;
}
