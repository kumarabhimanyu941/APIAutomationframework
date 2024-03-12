package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.Utils.*;
import com.qa.gorest.base.BaseTest;


public class CreateUserTest extends BaseTest{


  
   public RestClient getclient;
	
	@BeforeMethod
	public void createUserTest_setUp() {
	restclient=new RestClient(prop,baseURI);
	
	}
	
	
	@DataProvider
	public Object [][] getUserTestData(){
		return new Object[][] 
			{
			{"active","male"},
			{"inactive","female"},
			{"inactive","male"}
			};
		
	}
	
	@DataProvider
	public Object [][] getUserTestData_Sheet() {
		return ExcelUtil.getTestData(APIConstants.GOREST_USERDATA_SHEETNAME);
			
	}
	
	
	@Test(dataProvider = "getUserTestData")
	public void createUserTest(String gender,String status) {
	
	   //POST call
		User user = new User(StringUtils.generateRandomName(),StringUtils.generateRandomEmailId(),gender,status);
		
		int userId=restclient.post(GOREST_ENDPOINT, true,user, "json", true)
		.then().log().all()
		.extract().path("id");
		
		//GET call
		getclient = new RestClient(prop,baseURI);
		getclient.get(GOREST_ENDPOINT+"/"+userId, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode());
		
		
		
		
		
	}
	
	@Test(dataProvider="getUserTestData")
	public void createUserSchemaValidatonTest(String gender,String status) {
	
	   //POST call
		User user = new User(StringUtils.generateRandomName(),StringUtils.generateRandomEmailId(),gender,status);
		restclient.post(GOREST_ENDPOINT, true,user, "json", true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.and()
		.body(matchesJsonSchemaInClasspath("createuser.json"));
	
	
}
}
