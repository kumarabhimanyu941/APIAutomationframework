package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class GetUserTest extends BaseTest {

	public RestClient restclient;
	
	@BeforeMethod
	public void GetUserTest_setUp() {
		restclient = new RestClient(prop,baseURI);
		
	}
	
	
	
	
	@Test(priority=1)
	public void getAllUsersTest() {

		restclient.get(GOREST_ENDPOINT, true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}

	
	  @Test (priority=2)
	  
	  public void getSpecificUserTest() {
	  
	
	  restclient.get(GOREST_ENDPOINT+"/"+6129485,true,true) 
	  .then().log().all() 
	  .assertThat() .statusCode(APIHttpStatus.OK_200.getCode()) 
	  .and() 
	  .body("id", equalTo(6129485));
	  
	 }
	  
	  @Test(priority=3)
	   public void getSpecificUsersUsingQueryParams() {
	  
	  Map<String,String> queryParams= new HashMap<String,String>();
	  queryParams.put("name", "Urmila"); 
	  queryParams.put("status", "active");
	  
	   
	  restclient.get(GOREST_ENDPOINT,true,queryParams, true)
	  .then().log().all() 
	  .assertThat() .statusCode(APIHttpStatus.OK_200.getCode());
	  
	  
	  }
	 

}
