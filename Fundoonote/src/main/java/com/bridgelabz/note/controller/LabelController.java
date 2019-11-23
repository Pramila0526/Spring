package com.bridgelabz.note.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.note.dto.Labeldto;

import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.LabelserviceImp;
import com.bridgelabz.note.services.MessageReference;

@RestController

public class LabelController {
	
	
	@Autowired
	LabelserviceImp lableServiceImp;
	
	@PostMapping("/addLabel")
	public Response labelAdd(@RequestBody Labeldto labelDto,@RequestParam String token) {
		
		lableServiceImp.labelAdd(labelDto,token);
		return new Response(200, "lable add",MessageReference.LABEL_ADD_SUCCESSFULLY );
	}
	
	@DeleteMapping("/deleteLabel")
	public Response labelDelete (@RequestParam String id) {
		
		
		 lableServiceImp.labelDelete(id);
		return new Response(200, "label delete", MessageReference.LABEL_DELETE_SUCCESSFULLY);
	}
	@PutMapping("/updatLabel")
	public Response labelUpdate (@RequestBody Labeldto labeldto,@RequestParam String id)
	{
		lableServiceImp.labelUpdate(labeldto, id);
		return new Response(200, "label update", MessageReference.LABEL_UPDATE_SUCCESSFULLY);
	}
	
	@GetMapping("/searchLabel")
	public Response searchLabel(@RequestParam String id) {
		
		
		return new Response(200, "lable search", lableServiceImp.labelSearch(id));
	}
	
	
	@GetMapping("/showAllLabel")
	public  Response showAllLabel() {
		
		
		return new Response(200, "lable search", lableServiceImp.labelShowAll());
	}
	
	@PostMapping("/findLabelbyuserid")
	public  Response findByUserid(@RequestParam String userid) {
		
		System.out.println("in controller");
		
		return new Response(200, "lable find by user_id", lableServiceImp.findLabelByUser_id(userid));
	}
	
	@PostMapping("/assignnote")
	public Response assignNote(@RequestParam String noteid,@RequestParam String labelid) {
		
		
		return new Response(200, "assign note",lableServiceImp.assignNote(noteid, labelid));
	}
	
	
	
	
	
	
	
	

}
