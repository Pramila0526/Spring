/******************************************************************************
 *  Compilation:  javac -d bin LabelController.java
 *  Execution:      java -cp bin com.bridgelabz.elasticsearchserviceImp.createDocuemnt   note 
 *                  java -cp bin com.bridgelabz.elasticsearchserviceImp.SearchById       id 
 *                  java -cp bin com.bridgelabz.elasticsearchserviceImp.deleteDocuemnt   id 
 *                  java -cp bin com.bridgelabz.elasticsearchserviceImp.showAll   
 *                  java -cp bin com.bridgelabz.elasticsearchserviceImp.updatedocument   note  id
 *                  
 *                  
 *                  
 *                        
 *               
 *  
 *  Purpose:  create controller for    elastic search
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  3-12-2019
 *
 ******************************************************************************/
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.ElasticsearchserviceImp;

@RestController
@RequestMapping("/")
public class Elasticsearchcontroller {
		
	@Autowired
	ElasticsearchserviceImp elasticsearchserviceImp;   //create object  of elasticsearchserviceimp	
	
	/**
	 * @param note      provide detail of user note
	 * @return         note  detail add in elasticsearch return  add successfully or not
	 * @throws IOException
	 */
	@PostMapping("/create")
	public ResponseEntity<Response> createDocuemnt(@RequestBody Notemodel note) throws IOException{
		
		return new ResponseEntity<Response>((elasticsearchserviceImp.createDocuemnt(note)),HttpStatus.OK);
	}
	
	
	
	/**
	 * @param id    provide id for search perticular info of user note
	 * @return      if detail information is found retrun it or not
	 * @throws IOException
	 */
	@GetMapping("/read")
	public ResponseEntity<Response> SearchById( @RequestParam String id) throws IOException{
		return new ResponseEntity<Response>((elasticsearchserviceImp.readDocuement(id)),HttpStatus.OK);
		
	}
	
	/**
	 * @param id       user provide which detail want to delete it
	 * @return         if detail is delete return delete successfully or not
	 * @throws IOException
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteDocuemnt( @RequestParam String id) throws IOException{
		return new ResponseEntity<Response>((elasticsearchserviceImp.deleteDocuemnt(id)),HttpStatus.OK);
		
	}
	
	/**
	 * @return    show all detail in elasticsearach
	 * @throws IOException
	 */
	@GetMapping("/showall")
	public Response showAll() throws IOException {
           
		return new Response(200, " all notes ", elasticsearchserviceImp.findAll());
	}
	
	/**
	 * @param note  provide  detail of update information  of user note
	 * @param id    provide which user note want to update
	 * @return      if note  is update  retrun update successfully or not
	 * @throws IOException
	 */
	@PutMapping("/update")
	public ResponseEntity<Response> updatedocument(@RequestBody Notemodel note,@RequestParam String id) throws IOException {
        
		return new ResponseEntity<Response>((elasticsearchserviceImp.updateDocuemnt(note, id)),HttpStatus.CREATED);
	
	}

}
