package com.bridgelabz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

//generate automatically   getter  setter  using lombok

@Document
@Data
public class User {
	
	
	@Id
	private String id;
	private String name;
	private String address;
	private String email;
	private String password;
	private boolean validate;
	

}
