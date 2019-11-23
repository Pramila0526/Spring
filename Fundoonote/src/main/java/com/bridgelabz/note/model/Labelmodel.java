package com.bridgelabz.note.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection =   "label")
@Data
public class Labelmodel {
	
	@Id
	private String label_id;
	private String lable_title;
	private LocalDateTime created_date;
	private LocalDateTime updated_date;	
	private String userid;
	
	
	@DBRef(lazy = true)
	List<Notemodel> listOfNote=new ArrayList<Notemodel>();
	@Override
	public String toString() {
		return "Labelmodel [label_id=" + label_id + ", lable_title=" + lable_title + ", created_date=" + created_date
				+ ", updated_date=" + updated_date + ", user_id=" + userid + "]";
	}
	

}
