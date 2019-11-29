package com.bridgelabz.note.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.note.model.Labelmodel;



@Configuration
public class Redisconfig {
	
	
	@Bean
	JedisConnectionFactory jedisConnectionfactory() {
		return new JedisConnectionFactory();
	}
	
	
	@Bean
	public RedisTemplate<String ,Labelmodel> redisTemplate(){
		RedisTemplate<String, Labelmodel> redisTemplate=new RedisTemplate<String, Labelmodel>();
		redisTemplate.setConnectionFactory(jedisConnectionfactory());
		return redisTemplate;
	}

}
