package com.example.mongodb.repo;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodb.model.Book;

public interface Bookrepo extends MongoRepository<Book, Integer> {


}
