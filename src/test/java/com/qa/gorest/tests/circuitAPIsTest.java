package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.Utils.JsonPathValidator;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.response.Response;

public class circuitAPIsTest extends BaseTest {

	@Parameters({"baseURI"})
	@BeforeMethod
	public void circuitAPIsetUp(String baseURI) {
		restclient = new RestClient(prop,baseURI);
		
	}
	
	@Test
	public void getAllCircuitDetailsTest() {
		
		Response response=restclient.get(CIRCUITTEST_ENDPOINT,false, true);
	    int statuscode= response.getStatusCode();
	    System.out.println("Status Code is :"+statuscode);
		JsonPathValidator js= new JsonPathValidator(response);
		
		List<String> circuitNameList=js.readListResponse("$..circuitName");
		//System.out.println("Circuit Names are:"+circuitNameList);
		
		Assert.assertEquals(statuscode, APIHttpStatus.OK_200.getCode());
		Assert.assertTrue(circuitNameList.contains("Circuit of the Americas"));
		
	}
	
	
	
	
	
	
	
}
