package com.bridgelabz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@EnableRedisRepositories
public class Redisconfig {
	
	
	
	 @Value("${spring.redis.host}")
	   private String REDIS_HOSTNAME;
	   @Value("${spring.redis.port}")
	   private int REDIS_PORT;
	   @Bean
	   protected JedisConnectionFactory jedisConnectionFactory() {
	       RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
	       JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
	       JedisConnectionFactory factory = new JedisConnectionFactory(configuration,jedisClientConfiguration);
	       factory.afterPropertiesSet();
	       return factory;
	   }
	   @Bean
	   public RedisTemplate<String,Object> redisTemplate() {
	       final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
	       redisTemplate.setKeySerializer(new StringRedisSerializer());
	       redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
	       redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
	       redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
	       redisTemplate.setConnectionFactory(jedisConnectionFactory());
	       
	       return redisTemplate;
	   }
	   
	   
	   
	   
	
	
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
	
//
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//
//		RedisProperties properties = redisProperties();
//		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//		configuration.setHostName(properties.getHost());
//		configuration.setPort(properties.getPort());
//
//		return new JedisConnectionFactory(configuration);
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//
//		final RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//		return template;
//	}
//
//	@Bean
//	@Primary
//	public RedisProperties redisProperties() {
//
//		return new RedisProperties();
//
//	}
	
	
	
	
	
//	private net.sf.ehcache.CacheManager cacheManager;
//	@Bean
//    public CacheManager cacheManager() {
//       
//        cacheManager = net.sf.ehcache.CacheManager.create();
//        cacheManager.getConfiguration().setMaxBytesLocalHeap("16M");
//        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
//        ehCacheManager.setCacheManager(cacheManager);
//        return ehCacheManager;
//    }

}
