package com.sr.digidoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sr.digidoc.model.BaseResponse;
import com.sr.digidoc.model.Post;
import com.sr.digidoc.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@RequestMapping(value="api/posts",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> savePost(@RequestBody Post postObj){
		BaseResponse response = new BaseResponse();
		response.setStatusCode(0);
		response.setSuccessMsg("Post created successfully");
		postService.save(postObj);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="api/posts",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> getPost(@RequestParam("doctorId")Integer doctorId){
		List<Post> postList = postService.getPostByDoctor(doctorId);
		return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
	}
}
