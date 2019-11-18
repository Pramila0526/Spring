package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.model.Topic;

@org.springframework.stereotype.Service
public class Service {

	private List<Topic> topics =new ArrayList<>(Arrays.asList(
			new Topic("1", "collection", "core java"),
			new Topic("2", "multithreading", "core java"), 
			new Topic("3", "file handling", "core java")

	));

	public List<Topic> getAllTopics() {
		return topics;
	}

	public Topic getTopic(String id) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();

	}
	public void addTopic(Topic topic)
	{
		topics.add(topic);
	}

	public void updateTopic(String id, Topic topic) {
		
		for(int i=0;i<topics.size();i++)
		{
			Topic t=topics.get(i);
			if(t.getId().equals(id)) {
				topics.set(i, topic);
				return;
			}
		}
		
	}

	public void deleteTopic(  String id) {
		
		topics.removeIf(t -> t.getId().equals(id));
		
	
	}

}
