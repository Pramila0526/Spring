package com.bridgelabz.note.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Notedto {
	
	

	
	
	private String title;
	private String  description;
	private String color;
	private boolean pin;
	private boolean trash;
	private boolean archive;
	private boolean remider;
	

}
