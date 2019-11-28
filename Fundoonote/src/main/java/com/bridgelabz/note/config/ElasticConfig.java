package com.bridgelabz.note.config;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

	@Value("${elasticsearch.host}")
	private String elasticsearchHost;

	@Value("${elasticsearch.port}")
	private Integer elasticsearchPort;

	@Value("http")
	private String elasticsearchScheme;

	@Bean
	public RestHighLevelClient client() {

		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort, elasticsearchScheme)));
		return client;
	}

}
