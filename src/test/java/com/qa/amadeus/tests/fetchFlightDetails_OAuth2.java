package com.qa.amadeus.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.response.Response;

public class fetchFlightDetails_OAuth2 extends BaseTest{

	private String accesstoken;
	
	@Parameters({"grant_type","client_id","client_secret"})
	@BeforeMethod
	public void fetchFlightDetailssetUp(String grantType,String client_id,String client_secret)  {
		restclient = new RestClient(prop,baseURI);
		accesstoken=restclient.generateAccessToken(baseURI,"/v1/security/oauth2/token", grantType, client_id, client_secret);
		System.out.println("Access token value is :"+accesstoken);
		
	}
	
	@Test
	public void fetchFlightDetails() {
		
		Map<String,String> headersMap=new HashMap<String,String>();
		headersMap.put("Authorization", "Bearer "+accesstoken);
		
		Map<String,Object> queryParamsMap=new HashMap<String,Object>();
		queryParamsMap.put("origin", "PAR");
		queryParamsMap.put("maxPrice", 200);
		
		Response response=restclient.get("/v1/shopping/flight-destinations", false, headersMap, queryParamsMap, true);
		
		
		String data=response.jsonPath().get("data[0].type");
		System.out.println(data);
		
		
	}
	
	
	
	
	
	
}
