package com.bridgelabz.note.document;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "elesticnoteindex",type="elestictypeindex")
public class Notedocument {
	
	@Id
	private String noteId;
	
	private String title;
	private String  description;
	private  String email;

}
