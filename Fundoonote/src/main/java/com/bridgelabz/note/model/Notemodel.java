package com.bridgelabz.note.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document(collection = "note")

@Data
public class Notemodel {
	
	
	@Id
	private String id;
	
	
	private String title;
	private String  description;
	private String color;
	private boolean pin;
	private boolean trash;
	private boolean archive;
	private boolean remider;	
	private LocalDateTime updateDate;
	private LocalDateTime createDate;
	
	private String user_id;
	@DBRef
	List<Labelmodel> listOfLabels=new ArrayList<Labelmodel>();
	


	@Override
	public String toString() {
		return "Notemodel [id=" + id + ", title=" + title + ", description=" + description + ", color=" + color
				+ ", pin=" + pin + ", trash=" + trash + ", archive=" + archive + ", remider=" + remider
				+ ", createDate=" + createDate + "]"+ ", updateDate=" + updateDate + "]";
	}
	
	
	
	

}
