package com.example.mongodb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.model.Book;
import com.example.mongodb.repo.Bookrepo;

@RestController
public class Bookcontroller {

	@Autowired
	Bookrepo repo;

	@PostMapping("/addbook")
	public String addBook(@RequestBody Book book) {

		repo.save(book);

		return "adding book with id " + book.getId();

	}

	@GetMapping("/findAllbooks")
	public List<Book> getBooks() {
		return repo.findAll();
	}

	@GetMapping("/findAllBook/{id}")
	public Optional<Book> getBook(@PathVariable int id) {
		return repo.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repo.deleteById(id);
		return "book delete with id" + id;
	}
	
	@PutMapping("/update/{id}")
	public String updatebook( Book book, @PathVariable int id) {
		
		System.out.println(id);
	
		Book userupdate=repo.findById(id).get();
	     System.out.println("ss"+userupdate);	
		userupdate=book;
		
		repo.save(userupdate);
		return "Update succssfully...";
		
		
	}

}
