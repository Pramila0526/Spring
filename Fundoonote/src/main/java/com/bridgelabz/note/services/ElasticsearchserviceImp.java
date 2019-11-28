package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Responseutility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticsearchserviceImp implements Elasticsearchservice {

	private final String INDEX = "note";
	private final String TYPE = "notes";

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	private ObjectMapper mapper;

	
	@Override
	public Response createDocuemnt(Notemodel note) throws IOException {
		UUID uuid = UUID.randomUUID();
	
		Map<String, Object> map = mapper.convertValue(note, Map.class);
		
		IndexRequest indexrequest = new IndexRequest(INDEX, TYPE, note.getId()).source(map);
		IndexResponse indexresponse = client.index(indexrequest, RequestOptions.DEFAULT);
		Responseutility.CustomSucessResponse("HTTP_SUCCESS_MSG", note);
		return Responseutility.customSuccessResponse(indexresponse.getResult().name());

	}

	@Override
	public Response readDocuement(String id) throws IOException {
		
		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		Map<String, Object> resultMap = getResponse.getSource();
		
		return Responseutility.CustomSucessResponse("success",mapper.convertValue(resultMap, Notemodel.class));
		
	}

	@Override
	public String search(String searchstring) {
		// TODO Auto-generated method stub
		return null;
	}

}
