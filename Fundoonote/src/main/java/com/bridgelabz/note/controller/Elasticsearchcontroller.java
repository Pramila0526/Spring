package com.bridgelabz.note.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.ElasticsearchserviceImp;

@RestController
public class Elasticsearchcontroller {
	
	
	
	
	@Autowired
	ElasticsearchserviceImp elasticsearchserviceImp;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createDocuemnt(@RequestBody Notemodel note) throws IOException{
		
		return new ResponseEntity<Response>((elasticsearchserviceImp.createDocuemnt(note)),HttpStatus.OK);
	}

}
