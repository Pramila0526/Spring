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
	public Response labelAdd(@RequestBody Labeldto labelDto,@RequestParam String token) {
		
		lableServiceImp.labelAdd(labelDto,token);
		return new Response(200, "lable add",MessageReference.LABEL_ADD_SUCCESSFULLY );
	}
	
	
	/**
	 * @param id       user provide id for which use user want to delete
	 * @return           if label delete send response to  user  label delete successfully
	 */
	@DeleteMapping("/deleteLabel")
	public Response labelDelete (@RequestParam String id) {
		
		
		 lableServiceImp.labelDelete(id);
		return new Response(200, "label delete", MessageReference.LABEL_DELETE_SUCCESSFULLY);
	}
	
	/**
	 * @param labeldto     update label for user 
	 * @param id           user provide id for which use user want to update a record
	 * @return             if label update send response to  user  label update successfully
	 */
	@PutMapping("/updatLabel")
	public Response labelUpdate (@RequestBody Labeldto labeldto,@RequestParam String id)
	{
		lableServiceImp.labelUpdate(labeldto, id);
		return new Response(200, "label update", MessageReference.LABEL_UPDATE_SUCCESSFULLY);
	}
	
	
	/**
	 * @param id    user provide id for which use user want to search record
	 * @return      retrun the user found record
	 */
	@GetMapping("/searchLabel")
	public Response searchLabel(@RequestParam String id) {
		
		
		return new Response(200, "lable search", lableServiceImp.labelSearch(id));
	}
	
	
	/**
	 * @return   return all user  details
	 */
	@GetMapping("/showAllLabel")
	public  Response showAllLabel() {
		
		
		return new Response(200, "lable search", lableServiceImp.labelShowAll());
	}
	
	/**
	 * @param check userid  present or not in db
	 * @return if  user id found return it
	 */
	@PostMapping("/findLabelbyuserid")
	public  Response findByUserid(@RequestParam String userid) {
		
		System.out.println("in controller");
		
		return new Response(200, "lable find by user_id", lableServiceImp.findLabelByUser_id(userid));
	}
	
	/**
	 * @param noteid    user provide  noteid for mapping
	 * @param labelid   user provide  labelid for mapping
	 * @return          if assign note to label return sucess  message
	 */
	@PostMapping("/assignnote")
	public Response assignNote(@RequestParam String noteid,@RequestParam String labelid) {
		
		
		return new Response(200, "assign note",lableServiceImp.assignNote(noteid, labelid));
	}
	
	
	
	
	
	
	
	

}
