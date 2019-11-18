package com.example.demo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongodbconfig {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			MongoClient mongoclient=new  MongoClient("localhost",27017);
			
			MongoDatabase database=mongoclient.getDatabase("db");
			MongoCollection<Document> table=database.getCollection("database");
			
			
			Document doc=new Document("name","pandit walde");
			doc.append("id", 12);
			table.insertOne(doc);
			
			
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}

	}

}
