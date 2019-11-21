package com.bridgelabz.note.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.DateTimeContext;

import lombok.Data;

@Document

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
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date createdDate = new Date();

	@Override
	public String toString() {
		return "Notemodel [id=" + id + ", title=" + title + ", description=" + description + ", color=" + color
				+ ", pin=" + pin + ", trash=" + trash + ", archive=" + archive + ", remider=" + remider
				+ ", createdDate=" + createdDate + "]";
	}
	
	
	
	

}
