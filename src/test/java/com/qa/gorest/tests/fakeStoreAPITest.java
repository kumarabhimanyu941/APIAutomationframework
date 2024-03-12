package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class fakeStoreAPITest extends BaseTest{

	

	@Test
	public void getAllProductsTest() {
		
		restclient.get("/products",false, true)
		.then().log().all()
	    .assertThat()
		.statusCode(200);
		
	}
	
	
	
	
}
