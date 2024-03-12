package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

public class BaseTest {

	public static final String GOREST_ENDPOINT="/public/v2/users";
	public static final String CIRCUITTEST_ENDPOINT="/api/f1/2017/circuits.json";
	
	
	
	protected Properties prop;
	protected ConfigurationManager config;
	protected RestClient restclient;
	protected String baseURI;
	
	@BeforeTest
	@Parameters({"baseURI"})
	public void setUp(String baseURI) {
		config= new ConfigurationManager();
		prop=config.prop_init();
		//String baseURI=prop.getProperty("baseURI");
		//restclient = new RestClient(prop,baseURI);
		this.baseURI=baseURI;
	}
	
	
	
	
}
