package com.bridgelabz.note.repo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.note.model.Labelmodel;

@Repository
public class Redisrepository {

	
	public static String key ="label";
	private RedisTemplate< String , Labelmodel>
}
