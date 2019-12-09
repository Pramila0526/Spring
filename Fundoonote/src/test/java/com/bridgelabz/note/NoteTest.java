package com.bridgelabz.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bridgelabz.note.dto.Collabratordto;
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
	private Noterepository noterepo;
	@InjectMocks
	private NoteserviceImp noteserviceimp;
	@Mock
	private ElasticsearchserviceImp elasticsearchserviceImp;
	@Mock
	private ModelMapper mapper;
	@Mock
	private Tokenutility tokenutility;

	Date date;
	LocalDateTime datetime = LocalDateTime.now();
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1ZGU4OWQwZDE3ZjBlMTcyNDJiMDQzMjciLCJpYXQiOjE1NzU1MjU2NDV9.LruI0fUkD23CDeZLL9TTOS4pffXwIqVP20UL1iFMP98";
	String noteid = "5de62c4df3675a0154c6c634";
	String userid = "5dddff500ed0504ee89964f0";
	Notedto notedto = new Notedto();
	Notemodel notemodel = new Notemodel();
	Optional<Notemodel> idd = Optional.of(notemodel);
	List<Notemodel> list = new ArrayList<Notemodel>();
	Notemodel note1 = new Notemodel();
	List<String> list1 = new ArrayList<String>();
	Collabratordto collabratordto = new Collabratordto();

	@Test
	public void createNote() throws IOException {
		when(mapper.map(notedto, Notemodel.class)).thenReturn(notemodel);
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		notemodel.setUserid(userid);
		when(noterepo.save(notemodel)).thenReturn(notemodel);
		when(elasticsearchserviceImp.createDocuemnt(notemodel)).thenReturn(null);
		Response res = noteserviceimp.createNote(notedto, token);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void deleteNote() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		noterepo.delete(idd.get());
		Response res = noteserviceimp.deletePermanentNote(noteid, token);
		assertEquals(200, res.getStatus());
	}

	@Test
	public void searchNote() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		Response res = noteserviceimp.searchNote(noteid, token);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void updateNote() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		note1.setTitle(notedto.getColor());
		note1.setDescription(notedto.getDescription());
		note1.setColor(notedto.getColor());
		note1.setDate(datetime);
		when(noterepo.save(note1)).thenReturn(note1);
		Response res = noteserviceimp.UpdateNote(notedto, noteid, token);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void archive() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		notemodel.setArchive(false);
		when(noterepo.save(notemodel)).thenReturn(notemodel);
		Response res = noteserviceimp.trash(token, noteid);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void pin() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		notemodel.setPin(false);
		when(noterepo.save(notemodel)).thenReturn(notemodel);
		Response res = noteserviceimp.trash(token, noteid);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void trash() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		notemodel.setTrash(false);
		when(noterepo.save(notemodel)).thenReturn(notemodel);
		Response res = noteserviceimp.trash(token, noteid);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void addReminder() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		note1.setRemider(date);
		when(noterepo.save(note1)).thenReturn(note1);
		Response res = noteserviceimp.addReminder(date, noteid, token);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void removeReminder() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);
		when(noterepo.findByIdAndUserid(noteid, userid)).thenReturn(idd);
		note1.setRemider(null);
		when(noterepo.save(note1)).thenReturn(note1);
		Response res = noteserviceimp.removeReminder(noteid, token);
		assertEquals(200, res.getStatus());

	}

	@Test
	public void findLabelByUser_id() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);

	}

	@Test
	public void showAllNote() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		System.out.println(userid);
		when(noterepo.findByUserid(userid)).thenReturn(list);
		System.out.println(list);
		when(list.stream().filter(i -> !i.isTrash() && !i.isArchive()).collect(Collectors.toList()));
		System.out.println(list);
		assertEquals(200, list);

	}

	@Test
	public void sortByName() {

		when(tokenutility.getUserToken(token)).thenReturn(userid);
		List<?> notelist = (List<?>) noteserviceimp.sortNoteByName(token);
		assertEquals(notelist, noterepo.findAll());

	}

	@Test
	public void sortByDate() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);

		List<?> notelist = (List<?>) noteserviceimp.sortNoteByDate(token);
		assertEquals(notelist, noterepo.findAll());
	}

	@Test
	public void addCollabrator() {
		when(tokenutility.getUserToken(token)).thenReturn(userid);

		when(noterepo.findByIdAndUserid(collabratordto.getNoteId(), userid)).thenReturn(idd);
		list1 = note1.getCollabrators();
		list1.add(collabratordto.getColaboratorId());
		note1.setCollabrators(list1);
		when(noterepo.save(note1)).thenReturn(note1);
		Response res = noteserviceimp.addCollabrator(collabratordto, token);
		assertEquals(200, res.getStatus());

	}

}
