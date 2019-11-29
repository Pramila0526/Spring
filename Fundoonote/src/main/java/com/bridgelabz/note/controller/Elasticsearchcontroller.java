package com.bridgelabz.note.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	
	@GetMapping("/read")
	public ResponseEntity<Response> read( @RequestParam String id) throws IOException{
		return new ResponseEntity<Response>((elasticsearchserviceImp.readDocuement(id)),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteDocuemnt( @RequestParam String id) throws IOException{
		return new ResponseEntity<Response>((elasticsearchserviceImp.deleteDocuemnt(id)),HttpStatus.OK);
		
	}
	
	@GetMapping("/showall")
	public Response showAll() throws IOException {
           System.out.println("in controller");
		return new Response(200, " all notes ", elasticsearchserviceImp.findAll());
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response> updateInfo(@RequestBody Notemodel note,@RequestParam String id) throws IOException {
        
		return new ResponseEntity<Response>((elasticsearchserviceImp.updateDocuemnt(note, id)),HttpStatus.CREATED);
	
	}

}
