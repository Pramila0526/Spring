package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		//UUID uuid = UUID.randomUUID();
	    
		Map<String, Object> map = mapper.convertValue(note, Map.class);
		//System.out.println(uuid);
		IndexRequest indexrequest = new IndexRequest(INDEX, TYPE, note.getId()).source(map);
		System.out.println(indexrequest);
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
	
	
	

	@Override
	public Response deleteDocuemnt(String id) throws IOException {
		
		DeleteRequest deleterequest=new  DeleteRequest(INDEX, TYPE, id);
		DeleteResponse deleteresponse=client.delete(deleterequest, RequestOptions.DEFAULT);
		
		return new Response(200, "delete note", deleteresponse.getResult().name());
	}
	
	

	@Override
	public Response updateDocuemnt(Notemodel note, String id) throws IOException {
		
		System.out.println(id);
		Notemodel noteModel=findById(id);
	
		System.out.println("id"+noteModel.getId());
		System.out.println("gggg");
		UpdateRequest updateRequest=new UpdateRequest(INDEX, TYPE ,id);
		Map<String, Object>map=mapper.convertValue(note, Map.class);
		System.out.println(map);
		updateRequest.doc(map);
		
		UpdateResponse updateResponse=client.update(updateRequest, RequestOptions.DEFAULT);
		return new Response(200, "update note", updateResponse.getResult().name());

	}
	
	

	public Notemodel findById(String id) throws IOException {
	
	GetRequest request=new GetRequest(INDEX,TYPE,id);
	
	GetResponse getResponse=client.get(request, RequestOptions.DEFAULT);
	Map<String, Object> map=getResponse.getSource();
	
		return mapper.convertValue(map, Notemodel.class);
	}
	
	
	

	@Override
	public List<Notemodel> findAll() throws IOException {
		  System.out.println("1");
		 SearchRequest searchRequest = new SearchRequest();
		 System.out.println("2");
	        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	        System.out.println("3");
	        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
	        System.out.println("4");
	        searchRequest.source(searchSourceBuilder);
	        System.out.println("5");
	        SearchResponse searchResponse =
	                client.search(searchRequest, RequestOptions.DEFAULT);
	
	        return getSearchResult(searchResponse);
	}
	

	public List<Notemodel> getSearchResult(SearchResponse searchResponse) {
		System.out.println("search");
		SearchHit[] searchHit=searchResponse.getHits().getHits();
		List<Notemodel> list=new ArrayList<Notemodel>();
		if(searchHit.length>0) {
			Arrays.stream(searchHit).forEach(i ->list.add(mapper.convertValue(i.getSourceAsMap(), Notemodel.class)));
		}
		return list;
	}
	

}
