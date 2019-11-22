package com.bridgelabz.note.services;

import java.util.ArrayList;
import java.util.Optional;

import com.bridgelabz.note.dto.Labeldto;
import com.bridgelabz.note.model.Labelmodel;

public interface Labelservice {
	
	
	public void labelAdd(Labeldto labeldto,String token);
	public void labelDelete(String id);
	public void labelUpdate(Labeldto labeldto, String id);
	public ArrayList<Labelmodel> labelShowAll();
	public Optional<Labelmodel> labelSearch(String id);
	
	public ArrayList<Labelmodel> findLabelByUser_id(String user_id);
	

}
