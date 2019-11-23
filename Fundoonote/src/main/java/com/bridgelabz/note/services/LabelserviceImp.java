package com.bridgelabz.note.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.note.dto.Labeldto;
import com.bridgelabz.note.model.Labelmodel;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.repo.Labelrepository;
import com.bridgelabz.note.repo.Noterepository;
import com.bridgelabz.note.utility.Tokenutility;

@Component
public class LabelserviceImp implements Labelservice {

	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	Labelrepository labelRepo;
	
	@Autowired
	Noterepository noteRepo;
	
	@Autowired
	Tokenutility tokenUtility;
	
	@Override
	public void labelAdd(Labeldto labeldto,String token) {
		
		Labelmodel labelmodel=mapper.map(labeldto, Labelmodel.class);
		
		labelmodel.setLable_title(labeldto.getLable_title());
		LocalDateTime datetime=LocalDateTime.now();
		labelmodel.setCreated_date(datetime);
		
		String  user_id=tokenUtility.getUserToken(token);
		labelmodel.setUserid(user_id);
		labelRepo.save(labelmodel);
		
		
		
	}

	@Override
	public void labelDelete(String id) {
		
		 labelRepo.deleteById(id);
		
	}

	@Override
	public void labelUpdate(Labeldto labeldto, String id) {
		Labelmodel labelmodel=labelRepo.findById(id).get();
		
		labelmodel.setLable_title(labeldto.getLable_title());
		LocalDateTime datetime =LocalDateTime.now();
		labelmodel.setUpdated_date(datetime);
		
		labelRepo.save(labelmodel);
		
		
	}

	@Override
	public ArrayList<Labelmodel> labelShowAll() {
		
		return (ArrayList<Labelmodel>) labelRepo.findAll();
		
	}
	
	

	@Override
	public Optional<Labelmodel> labelSearch(String id) {
		
		return  labelRepo.findById(id);
		
	}

	@Override
	public ArrayList<Labelmodel> findLabelByUser_id(String user_id) {
		
		System.out.println(user_id);
		return (ArrayList<Labelmodel>) labelRepo.findByUserid(user_id);
	}

	@Override
	public String assignNote(String noteid, String labelid) {
		
		Labelmodel label=labelRepo.findById(labelid).get();
		if(label==null)
		{
		 return "label id not found";
		}
		Notemodel note=noteRepo.findById(noteid).get();
		if(note==null)
		{
		 return "label id not found";
		}
		List<Notemodel> notelist=new ArrayList<Notemodel>();
		List<Labelmodel> labellist=new ArrayList<Labelmodel>();
	
		 
		labellist.add(label);
		notelist.add(note);
		
		label.setListOfNote(label.getListOfNote());
		note.setListOfLabels(note.getListOfLabels());
	  	
		 labelRepo.save(label);
	  	 noteRepo.save(note);
	  	 
	  	 return "sucess";
		 
	}

	
	
}
