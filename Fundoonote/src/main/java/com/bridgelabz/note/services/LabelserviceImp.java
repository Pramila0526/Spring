/******************************************************************************
 *  Compilation:  javac -d bin Tokenutility.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create utility for jwt  response in  token
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  19-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.bridgelabz.note.dto.Labeldto;
import com.bridgelabz.note.exception.custom.Labelnotfoundexception;
import com.bridgelabz.note.exception.custom.Notenotfoundexception;
import com.bridgelabz.note.model.Labelmodel;
import com.bridgelabz.note.model.Notemodel;

import com.bridgelabz.note.repo.Labelrepository;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Tokenutility;

/**
 * @author user
 *
 */
@Component



public class LabelserviceImp implements Labelservice {
	static Logger logger = LoggerFactory.getLogger(LabelserviceImp.class);

	@Autowired
	private ModelMapper mapper; // create modelmapper object
	@Autowired
	private Labelrepository labelRepo; // create lable repository object

	@Autowired
	private Noterepository noteRepo; // create Noterepository object

	@Autowired
	private Tokenutility tokenUtility; // create Tokenutility object

	/**
	 * purpose add new user label
	 * 
	 * @return
	 */
	
	

	@Cacheable(value = "labeldto", key = "#token")
	@Override
	public Response labelAdd(Labeldto labeldto, String token) {
        System.out.println("1");
		Labelmodel labelmodel = mapper.map(labeldto, Labelmodel.class);
		labelmodel.setLable_title(labeldto.getLable_title());
		LocalDateTime datetime = LocalDateTime.now();
		labelmodel.setCreated_date(datetime);
		System.out.println("2");
		String user_id = tokenUtility.getUserToken(token);
		labelmodel.setUserid(user_id);
		labelRepo.save(labelmodel);
		System.out.println("1");
		return new Response(200, "lable add", MessageReference.LABEL_ADD_SUCCESSFULLY);

	}
	
	
	
	

	/**
	 * purpose delete perticular label
	 */

	
	@Override
	public Response labelDelete(String id) {

		Optional<Labelmodel> label = labelRepo.findById(id);
		System.out.println(label);
		if (label.isEmpty()) {
			throw new Labelnotfoundexception(MessageReference.LABEL_NOT_FOUND);
		}

		labelRepo.deleteById(id);
		return new Response(200, "label delete", MessageReference.LABEL_DELETE_SUCCESSFULLY);

	}

	/**
	 * purpose update perticular label
	 */
	@Override
	public Response labelUpdate(Labeldto labeldto, String id) {
		Labelmodel labelmodel = labelRepo.findById(id).get();

		if (labelmodel == null) {
			throw new Labelnotfoundexception(MessageReference.LABEL_NOT_FOUND);
		}
		labelmodel.setLable_title(labeldto.getLable_title());
		LocalDateTime datetime = LocalDateTime.now();
		labelmodel.setUpdated_date(datetime);

		labelRepo.save(labelmodel);

		return new Response(200, "label update", MessageReference.LABEL_UPDATE_SUCCESSFULLY);

	}

	/**
	 * purpose show all user label
	 */
	@Override
	public ArrayList<Labelmodel> labelShowAll() {

		return (ArrayList<Labelmodel>) labelRepo.findAll();

	}

	/**
	 * purpose Search a perticular user label
	 */
	@Override
	public Response labelSearch(String id) {
		System.out.println("dddd");
		Optional<Labelmodel> labelmodel = labelRepo.findById(id);
		if (labelmodel.isEmpty()) {
			throw new Labelnotfoundexception(MessageReference.LABEL_NOT_FOUND);
		}
		return new Response(200, "  user label ", labelRepo.findById(id));
	}

	/**
	 * purpose find by user id for label
	 */
	@Override
	public Response findLabelByUser_id(String user_id) {

		return new Response(200, "  user label ", labelRepo.findByUserid(user_id));
	}

	/**
	 * purpose main goal is relationship between label and note
	 */
	@Override
	public Response manyToMany(String noteid, String labelid) {

		List<Notemodel> notelist = new ArrayList<Notemodel>();
		List<Labelmodel> labellist = new ArrayList<Labelmodel>();
		Labelmodel label = labelRepo.findById(labelid).get(); // check label id present or not
		if (label == null) {
			throw new Notenotfoundexception(MessageReference.NOTE_NOT_FOUND);

		}
		Notemodel note = noteRepo.findById(noteid).get(); // check note id present or not
		if (note == null) {
			throw new Labelnotfoundexception(MessageReference.LABEL_NOT_FOUND);

		}

		labellist.add(label);
		notelist.add(note);
		label.setListOfNote(notelist);
		note.setListOfLabels(labellist);
		labelRepo.save(label);
		noteRepo.save(note);

		return new Response(200, "assign note", " mapping sucess");

	}

}
