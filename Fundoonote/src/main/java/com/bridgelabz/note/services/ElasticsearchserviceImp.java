package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

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
public class ElasticsearchserviceImp  implements Elasticsearchservice{

	private  final String INDEX="note";
    private  final String TYPE="notes";
	
	@Autowired
	private RestHighLevelClient  client;
	
	@Autowired
	private ObjectMapper mapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public Response createDocuemnt(Notemodel note) throws IOException {
		UUID uuid=UUID.randomUUID();
		System.out.println(uuid);
		Map<String, Object>map=mapper.convertValue(note, Map.class);
		System.out.println("dd"+map);
		IndexRequest indexrequest = new IndexRequest(INDEX, TYPE, note.getId().toString()).source(map);
	
		
		IndexResponse indexresponse=client.index(indexrequest, RequestOptions.DEFAULT);
		
		Responseutility.CustomSucessResponse("HTTP_SUCCESS_MSG", note);

		return Responseutility.customSuccessResponse(indexresponse.getResult().name());
		
	}

	@Override
	public String readDocuement(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String search(String searchstring) {
		// TODO Auto-generated method stub
		return null;
	}

}
