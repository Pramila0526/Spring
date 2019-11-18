package com.example.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Data
public class Book {
	
	@Id
	private int id;
	private String bookName;
	private String authorName;
	

}
