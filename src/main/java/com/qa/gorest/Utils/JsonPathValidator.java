package com.qa.gorest.Utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidator {

	public String responsebody;

	public  JsonPathValidator(Response response) {
		responsebody=response.getBody().asString();

	}


	public <T> T read(String jsonPath) {
		return JsonPath.read(responsebody, jsonPath);

	}

	public <T> List<T> readListResponse(String jsonPath) {
		return JsonPath.read(responsebody, jsonPath);
	}




	public <T> List<Map<String,T>> readListOfMapResponse(String jsonPath) {
		return JsonPath.read(responsebody, jsonPath);
	}

}
