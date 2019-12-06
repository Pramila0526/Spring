package com.bridgelabz.note.notetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.NoteserviceImp;
import com.bridgelabz.note.utility.Tokenutility;

@SpringBootTest
public class NoteServiceTest {
	@Mock
	Noterepository noteRepo;
	
	@InjectMocks
	NoteserviceImp service;	
	@Mock
	ModelMapper mapper;	
	@Mock
	Tokenutility tokenutility; 
	
	String token = "kdasklfnasjfawf";
	String email = "abcd@gmail.com";
	Notemodel mod = new Notemodel();
	String id="adsdfs";
	
	@Test
	public void createNoteTest() throws IOException {
		Notedto dto = new Notedto();
		dto.setTitle("abcd");
		dto.setDescription("efgh");		
		when(mapper.map(dto,Notemodel.class)).thenReturn(mod);
		when(tokenutility.getUserToken(token)).thenReturn(id);
		doNothing().when(noteRepo.save(mod));
		Response resp  = service.createNote(dto, token);
		assertEquals(200,resp.getStatus());
		
		
		
	}
	@Test
	public  void showAllnote()throws IOException{
		
		when(tokenutility.getUserToken(token)).thenReturn(id);
		Notemodel note1=new Notemodel();
		note1.setId("dggg");
		note1.setTitle("abc");
		note1.setDescription("xyz");
		note1.setColor("red");
		note1.isArchive();
		note1.isTrash();
		note1.isPin();
		Notemodel note2=new Notemodel();
		note2.setId("dggg");
		note2.setTitle("abc");
		note2.setDescription("xyz");
		note2.setColor("red");
		note2.isArchive();
		note2.isTrash();
		note2.isPin();
		List<Notemodel> list=new ArrayList<Notemodel>();
		list.add(note1);
		list.add(note2);
		List<Notemodel>getAllnote=service.showAllNote(token);
		when(noteRepo.findByUserid(id)).thenReturn(getAllnote);
		assertEquals("pandit", getAllnote.get(0).getTitle());

		
	} 
	public void searchNote() {
		
	}
	
}
