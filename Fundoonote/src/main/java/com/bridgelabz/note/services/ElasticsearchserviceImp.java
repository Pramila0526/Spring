/******************************************************************************
 *  Compilation:  javac -d bin LabelserviceImp.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create serviceimp class for write all logic of elastic search
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  3-12-2019
 *
 ******************************************************************************/
package com.bridgelabz.note.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.note.model.Notemodel;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Responseutility;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticsearchserviceImp implements Elasticsearchservice {

	private final String INDEX = "note"; // create for elastic search same like as database schema
	private final String TYPE = "notes"; // create for elastic search same like database table

	@Autowired
	private RestHighLevelClient client; // create object of resthighlevelclient

	@Autowired
	private ObjectMapper mapper; // create object of object mapper for mapping purpose

	@Autowired
	ElasticsearchserviceImp elasticsearchserviceImp; // create object of elasticsearchserviceimp

	/**
	 * purpose storing all data in elastic search
	 */
	@Override
	public Response createDocuemnt(Notemodel note) throws IOException {

		Map<String, Object> map = mapper.convertValue(note, Map.class);

		IndexRequest indexrequest = new IndexRequest(INDEX, TYPE, note.getId()).source(map); // storing note data
																								// converting json
																								// format

		IndexResponse indexresponse = client.index(indexrequest, RequestOptions.DEFAULT); // A response of an index
																							// operation,

		Responseutility.CustomSucessResponse("HTTP_SUCCESS_MSG", note);
		return Responseutility.customSuccessResponse(indexresponse.getResult().name()); // The change that occurred to
																						// the document

	}

	/**
	 * purpose reading perticular details
	 */
	/**
	 *
	 */
	@Override
	public Response readDocuement(String id) throws IOException {

		GetRequest getRequest = new GetRequest(INDEX, TYPE, id); // get information index base
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT); // The response of a get action.
		Map<String, Object> resultMap = getResponse.getSource(); // coverting all detail in map

		return Responseutility.CustomSucessResponse("success", mapper.convertValue(resultMap, Notemodel.class));

	}

	@Override
	public String search(String searchstring) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * purpose delete perticular user note in elastic search details
	 */

	@Override
	public Response deleteDocuemnt(String id) throws IOException {

		DeleteRequest deleterequest = new DeleteRequest(INDEX, TYPE, id);// delete document by index base using id
		DeleteResponse deleteresponse = client.delete(deleterequest, RequestOptions.DEFAULT); // The response of the
																								// delete action

		return new Response(200, "delete note", deleteresponse.getResult().name()); // The change that occurred to the
																					// document
	}

	/**
	 * purpose delete perticular user note in elastic search details
	 */
	@Override
	public Response updateDocuemnt(Notemodel note, String id) throws IOException {

		Notemodel noteModel = findById(id);

		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id); // create object of update request
		Map<String, Object> map = mapper.convertValue(note, Map.class);

		updateRequest.doc(map); // mapping a value
		// Sets the doc to use for updates when a script is not specified.

		UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT); // update response for new
																								// data
		return new Response(200, "update note", updateResponse.getResult().name());

	}

	public Notemodel findById(String id) throws IOException {

		GetRequest request = new GetRequest(INDEX, TYPE, id);

		GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
		Map<String, Object> map = getResponse.getSource();

		return mapper.convertValue(map, Notemodel.class);
	}

	/**
	 * purpose fetching all user note in elastic search
	 */
	@Override
	public List<Notemodel> findAll() throws IOException {

		SearchRequest searchRequest = new SearchRequest();

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.query(QueryBuilders.matchAllQuery()); // match all details

		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		return getSearchResult(searchResponse);
	}

	public List<Notemodel> getSearchResult(SearchResponse searchResponse) {
		System.out.println("search");
		SearchHit[] searchHit = searchResponse.getHits().getHits();
		List<Notemodel> list = new ArrayList<Notemodel>();
		if (searchHit.length > 0) {
			Arrays.stream(searchHit).forEach(i -> list.add(mapper.convertValue(i.getSourceAsMap(), Notemodel.class)));
		}
		return list;
	}

	/**
	 * purpose search perticluar description of user note if description is found
	 * return all note or not
	 */
	@Override
	public Response searchByDescription(String description) throws IOException {
		SearchRequest searchRequest = new SearchRequest(); // create object of differnce search option
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); // A search source builder allowing to
																				// easily build search source
		searchSourceBuilder.query( // Sets the search query for this request.
				QueryBuilders.boolQuery() // A Query that matches documents matching boolean combinations of other
											// queries.
						.should(QueryBuilders.queryStringQuery(description). // Adds a clause that should be matched by
																				// the returned documents
								lenient(true).field("description"))); // Sets the query string parser to be lenient when
																		// parsing field values
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT); // A response of a search
																								// request

		return new Response(200, "user title", getSearchResult(searchResponse));

	}

	/**
	 * purpose search perticluar title of user note if description is found return
	 * all note or not
	 */
	@Override
	public Response searchByTitle(String title) throws IOException {

		SearchRequest searchRequest = new SearchRequest(); // create object of differnce search option

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); // A search source builder allowing to
																				// easily build search source

		searchSourceBuilder.query(
				QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(title).lenient(true).field("title")));

		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		return new Response(200, "user title", getSearchResult(searchResponse));

	}

}
