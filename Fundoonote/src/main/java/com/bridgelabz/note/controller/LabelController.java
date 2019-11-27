/******************************************************************************
 *  Compilation:  javac -d bin LabelController.java
 *  Execution:      java -cp bin com.bridgelabz.Lebelserviceimp.labelAdd    labeldto , token
 *                  java -cp bin com.bridgelabz.Lebelserviceimp.labelDelete    id
 *                  java -cp bin com.bridgelabz.Lebelserviceimp.labelUpdate    id
 *                  java -cp bin com.bridgelabz.Lebelserviceimp.labelSearch    id
 *                  java -cp bin com.bridgelabz.Lebelserviceimp.labelShowAll   
 *                  java -cp bin com.bridgelabz.Lebelserviceimp.findLabelByUser_id    id
 *                  java -cp bin com.bridgelabz.Lebelserviceimp.assignNote noteid,labelid    
 *                        
 *               
 *  
 *  Purpose:       create controller for   user label
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.controller;


import javax.validation.Valid;

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

import com.bridgelabz.note.dto.Labeldto;

import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.LabelserviceImp;


/**
 * @author user
 *
 */
@RestController

public class LabelController {
	
	
	@Autowired
	LabelserviceImp lableServiceImp;   //create object  labelserviceImp class
	
	
	/**
	 * @param labelDto   create label for user 
	 * @param token      give user token
	 * @return           if label add send response to  user  label add successfully
	 */
	@PostMapping("/addLabel")
	public ResponseEntity<Response> labelAdd(@Valid @RequestBody Labeldto labelDto,@RequestParam String token) {
		
		lableServiceImp.labelAdd(labelDto,token);
		return new ResponseEntity<Response>(lableServiceImp.labelAdd(labelDto,token), HttpStatus.OK);

	}
	
	
	/**
	 * @param id       user provide id for which use user want to delete
	 * @return           if label delete send response to  user  label delete successfully
	 */
	@DeleteMapping("/deleteLabel")
	public ResponseEntity<Response> labelDelete(@Valid @RequestParam String id) {

		return new ResponseEntity<Response>(lableServiceImp.labelDelete(id), HttpStatus.OK);

	}
	
	/**
	 * @param labeldto     update label for user 
	 * @param id           user provide id for which use user want to update a record
	 * @return             if label update send response to  user  label update successfully
	 */
	@PutMapping("/updatLabel")
	public ResponseEntity<Response> labelUpdate (@Valid @RequestBody Labeldto labeldto,@RequestParam String id)
	{
		
		return new ResponseEntity<Response>(lableServiceImp.labelUpdate(labeldto, id), HttpStatus.OK);
		
	}
	
	
	/**
	 * @param id    user provide id for which use user want to search record
	 * @return      retrun the user found record
	 */
	@GetMapping("/searchLabel")
	public  ResponseEntity<Response>  searchLabel(@Valid @RequestParam String id) {
		
		
		return new ResponseEntity<Response>(lableServiceImp.labelSearch(id), HttpStatus.OK);
		
	}
	
	
	/**
	 * @return   return all user  details
	 */
	@GetMapping("/showallabel")
	public  Response showAllLabel() {
		
		
		return new Response(200, " all lable ", lableServiceImp.labelShowAll());
	}
	
	/**
	 * @param check userid  present or not in db
	 * @return if  user id found return it
	 */
	@PostMapping("/findLabelbyuserid")
	public   ResponseEntity<Response>  findByUserid(@Valid @RequestParam String userid) {
		

		
		return new ResponseEntity<Response>(lableServiceImp.findLabelByUser_id(userid), HttpStatus.OK);
	
	}
	
	
	
	/**
	 * @param noteid    user provide  noteid for mapping
	 * @param labelid   user provide  labelid for mapping
	 * @return          if assign note to label return sucess  message
	 */
		
	@PostMapping("/manytomany")
	public  ResponseEntity<Response>  manyToMany(@Valid @RequestParam String noteid,@RequestParam String labelid) {
		
		
		return new ResponseEntity<Response>(lableServiceImp.manyToMany(noteid, labelid), HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	

}
