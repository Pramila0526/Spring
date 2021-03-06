package com.bridgelabz.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private Labelrepository labelrepo;

	@InjectMocks
	private LabelserviceImp labelserviceImp;

	@Mock
	private ModelMapper mapper;
	@Mock
	private Tokenutility tokenutility;

	String token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ZGU4OWQwZDE3ZjBlMTcyNDJiMDQzMjciLCJpYXQiOjE1NzU1MjU2NDV9.LruI0fUkD23CDeZLL9TTOS4pffXwIqVP20UL1iFMP98";
	String labelid ="5dd8dfd9086ed83f82560eee";
	String userid = "5dddff500ed0504ee89964f0";
	Labelmodel labelmodel = new Labelmodel();
	Optional<Labelmodel> idd1 = Optional.of(labelmodel);
	Labeldto labeldto = new Labeldto();
	LocalDateTime datetime = LocalDateTime.now();

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
		when(labelrepo.findByIdAndUserid(labelid, userid)).thenReturn(idd1);
		System.out.println("2");
		when(labelrepo.findById(userid)).thenReturn(idd1);
		System.out.println("3");
		Response res = labelserviceImp.labelSearch(labelid, token);
		System.out.println("4");
		assertEquals(200, res.getStatus());
		System.out.println("5");

	}

	@Test
	public void DeleteLabel() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);

		when(labelrepo.findByIdAndUserid(labelid, userid)).thenReturn(idd1);
		System.out.println("label" + idd1);
		 labelrepo.deleteById(idd1);
		Response res = labelserviceImp.labelDelete(labelid, token);
		assertEquals(200, res.getStatus());
		// when(labelrepo.delete(label);
	}

	@Test
	public void labelUpdate() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(labelrepo.findByIdAndUserid(labelid, userid)).thenReturn(idd1);
		labelmodel.setLable_title(labeldto.getLable_title());
		labelmodel.setUpdated_date(datetime);
		when(labelrepo.save(labelmodel)).thenReturn(labelmodel);
		Response res=labelserviceImp.labelUpdate(labeldto, labelid, token);
		assertEquals(200, res.getStatus());		
		
		
	}

	@Test
	public void showAllLabel() {
		
		ArrayList< Labelmodel> list=new ArrayList<Labelmodel>();
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(labelrepo.findByUserid(userid)).thenReturn(list);	
		when(labelserviceImp.labelShowAll(token)).thenReturn(list);		
		assertEquals(200, list);
		
		
	}
	@Test
	public void findLabelByUser_id() {
		
	}
	
	@Test
	public void manyToMany() {
		
	}

}
