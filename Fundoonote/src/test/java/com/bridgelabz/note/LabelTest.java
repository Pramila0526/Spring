package com.bridgelabz.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.note.dto.Labeldto;
import com.bridgelabz.note.model.Labelmodel;
import com.bridgelabz.note.repo.Labelrepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.services.LabelserviceImp;
import com.bridgelabz.note.utility.Tokenutility;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig
public class LabelTest {

	@Mock
	Labelrepository labelrepo;

	@InjectMocks
	LabelserviceImp labelserviceImp;

	@Mock
	ModelMapper mapper;
	@Mock
	Tokenutility tokenutility;
	
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ZGU4OWQwZDE3ZjBlMTcyNDJiMDQzMjciLCJpYXQiOjE1NzU1MjU2NDV9.LruI0fUkD23CDeZLL9TTOS4pffXwIqVP20UL1iFMP98";
	
	String idd="ggffgg";
	String labelid="5dd8dfd9086ed83f82560eee";
	
	String userid="5dddff500ed0504ee89964f0";

	Labelmodel labelmodel = new Labelmodel();
	Optional<Labelmodel> label=Optional.of(labelmodel);
	Labeldto labeldto = new Labeldto();

	
	
	@Test
	public void createLabel() {       
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(mapper.map(labeldto, Labelmodel.class)).thenReturn(labelmodel);
		labelmodel.setUserid(userid);	
		when(labelrepo.save(labelmodel)).thenReturn(labelmodel);		
		Response res = labelserviceImp.labelAdd(labeldto, token);
		assertEquals(200, res.getStatus());

	}
	
	@Test
	public void searchLabel() {	
	
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		System.out.println("1");
	    when(labelrepo.findByIdAndUserid(labelid, userid)).thenReturn(label);
		System.out.println("2");
	    when(labelrepo.findById(userid)).thenReturn(label);
		System.out.println("3");
	    Response res=labelserviceImp.labelSearch(labelid, token);
		System.out.println("4");
	    assertEquals(200, res.getStatus());
		System.out.println("5");
	    
		
	}
	
	@Test
	public void DeleteLabel() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		
		 when(labelrepo.findByIdAndUserid(labelid, userid)).thenReturn(label);
		 System.out.println("label"+label);
		 // when(labelrepo.delete(labelid));
		 Response res=labelserviceImp.labelDelete(labelid, token);
		 assertEquals(200, res.getStatus());
		// when(labelrepo.delete(label);
	}
	
	
//	@Test
//	public void showAllLabel() {
//		
//		ArrayList< Labelmodel> list=new ArrayList<Labelmodel>();
//		when(tokenutility.getUserToken(token)).thenReturn(id);
//		when(labelrepo.findByIdAndUserid(labelid, id)).thenReturn(userid);
//		when(labelserviceImp.labelShowAll(token)).thenReturn(list);
//		
//		assertEquals(200, list);
//		
//		
//	}
	

}
