package com.bridgelabz.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.NoteserviceImp;
@RunWith(SpringRunner.class)
@SpringBootTest
class FundoonoteApplicationTests {

	@InjectMocks
	NoteserviceImp service;
	@Mock
	Noterepository repo;
	
	private String id="1";
	private String email="panditwalde64@gmail.com";
	
	Response res=null;
	
	
	
//	@Test
//	public void getAllNote() {
//		
//		Notemodel note=new Notemodel();
//		note.setId(id);
//		note.setTitle("good evening");
//		note.setDescription("friday");
//		note.setColor("red");
//		note.isArchive();
//		note.isPin();
//		note.isTrash();
//		
//		
//	}
//	
	
//	@Test
//	public void   searchNote(String noteid,String token) {
//		when(service.searchNote(noteid, token)).thenReturn(res);
//		Response  response=service.searchNote(noteid, token);
//		assertEquals(200, response.getStatus());
//		
//	}
//	
	
//	void contextLoads() {
//	}
	
	@Test
	public void testSearch(String token) {
		//when(service.searchNote(id)).thenReturn(res);
		Response ress = service.searchNote(id, token);
		assertEquals(200, ress.getStatus());
	}
//	
//	
//	@Test
//	public  void showAllNote(String token) {
//		
//		Response ress=(Response) service.showAllNote(token);
//		assertEquals(200,ress);
//	}
//	
	
}
