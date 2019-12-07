package com.bridgelabz.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.note.dto.Notedto;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.ElasticsearchserviceImp;
import com.bridgelabz.note.services.NoteserviceImp;
import com.bridgelabz.note.utility.Tokenutility;


@SpringJUnitConfig
@RunWith(SpringJUnit4ClassRunner.class)
public class NoteTest {
	
	
	@Mock
	Noterepository noterepo;
	@InjectMocks
	NoteserviceImp noteserviceimp;
	
	@Mock
	ElasticsearchserviceImp elasticsearchserviceImp;
	
	@Mock
	ModelMapper mapper;
	
	@Mock
	Tokenutility tokenutility;
	
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ZGU4OWQwZDE3ZjBlMTcyNDJiMDQzMjciLCJpYXQiOjE1NzU1MjU2NDV9.LruI0fUkD23CDeZLL9TTOS4pffXwIqVP20UL1iFMP98";
	String noteid="5de62c4df3675a0154c6c634";
	String userid = "5dddff500ed0504ee89964f0";
	Notedto notedto=new Notedto();
    Notemodel notemodel=new Notemodel();
    Optional<Notemodel>idd=Optional.of(notemodel);
	
	@Test
	public void createNote() throws IOException {	
		when(mapper.map(notedto, Notemodel.class)).thenReturn(notemodel);
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		notemodel.setUserid(userid);
		when(noterepo.save(notemodel)).thenReturn(notemodel);
	    when(elasticsearchserviceImp.createDocuemnt(notemodel)).thenReturn(null);
		Response res=noteserviceimp.createNote(notedto, token);
		assertEquals(200, res.getStatus());
		
	}
	
	
	@Test
	public void deleteNote() {
	
		when(tokenutility.getUserToken(token)).thenReturn(userid);		
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);	
		Response res=noteserviceimp.deletePermanentNote(noteid, token);		
		assertEquals(200, res.getStatus());
	}
	
	
	@Test
	public void  searchNote() {
		
		when(tokenutility.getUserToken(token)).thenReturn(userid);		
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		Response res=noteserviceimp.searchNote(noteid, token);
		assertEquals(200, res.getStatus());
		
		
		
	}
	@Test
	public void updateNote() {
		
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);	
		
		
	}
	
	

}
