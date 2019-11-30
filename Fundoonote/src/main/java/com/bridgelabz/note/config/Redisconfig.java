package com.bridgelabz.note.config;

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
	
	
	
	
	
//	 private final Logger log = (Logger) LoggerFactory.getLogger(CacheConfiguration.class);
//
//	    private net.sf.ehcache.CacheManager cacheManager;
//
//	    @PreDestroy
//	    public void destroy() {
//	        cacheManager.shutdown();
//	    }
//
//	    @Bean
//	    public CacheManager cacheManager() {
//	        log.debug("Starting Ehcache");
//	        cacheManager = net.sf.ehcache.CacheManager.create();
//	        cacheManager.getConfiguration().setMaxBytesLocalHeap("16M");
//	        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
//	        ehCacheManager.setCacheManager(cacheManager);
//	        return ehCacheManager;
//	    }
//	
	
	
	
	

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {

		RedisProperties properties = redisProperties();
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(properties.getHost());
		configuration.setPort(properties.getPort());

		return new JedisConnectionFactory(configuration);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {

		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		return template;
	}

	@Bean
	@Primary
	public RedisProperties redisProperties() {

		return new RedisProperties();

	}
	


}
