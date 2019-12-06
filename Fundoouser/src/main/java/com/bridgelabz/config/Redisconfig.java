package com.bridgelabz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;



@Configuration
@EnableRedisRepositories
public class Redisconfig {

	
	 @Value("${spring.redis.host}")    //
	   private String REDIS_HOSTNAME;    
	   @Value("${spring.redis.port}")
	   private int REDIS_PORT;
//	   @Bean
//	   protected JedisConnectionFactory jedisConnectionFactory() {
//	       RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
//	       JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
//	       JedisConnectionFactory factory = new JedisConnectionFactory(configuration,jedisClientConfiguration);
//	       factory.afterPropertiesSet();
//	       return factory;
//	   }
//	   @Bean
//	   public RedisTemplate<String,Object> redisTemplate() {
//	       final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
//	       redisTemplate.setKeySerializer(new StringRedisSerializer());
//	       redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
//	       redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
//	       redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//	       redisTemplate.setConnectionFactory(jedisConnectionFactory());
//	       
//	       return redisTemplate;
//	   }
	   @Bean
		public JedisConnectionFactory jedisConnectionFactory() {
			RedisProperties properties = redisProperties();
			RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
			configuration.setHostName(properties.getHost());
			configuration.setPort(properties.getPort());
			// configuration.setPassword(properties.getPassword());
			JedisConnectionFactory factoryObject = new JedisConnectionFactory(configuration);
			factoryObject.getPoolConfig().setMaxIdle(30);
			factoryObject.getPoolConfig().setMinIdle(10);
			return factoryObject;
		}

		/**
		 * Method to generate a redis template object with jedis connection factory
		 * object.
		 * 
		 * @return redis template object.
		 */
		@Bean
		public RedisTemplate<String, Object> redisTemplate() {
			final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
			template.setConnectionFactory(jedisConnectionFactory());
			template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
			return template;
		}

		/**
		 * Method to generate redis property object.
		 * 
		 * @return redis properties object.
		 */
		@Bean
		@Primary
		public RedisProperties redisProperties() {
			return new RedisProperties();
		}


	
	
	
	

}
