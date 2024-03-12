package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

	Properties prop;
	FileInputStream ip;
	
	public Properties prop_init() {
		prop = new Properties();
		
		String envName=System.getProperty("env");
		try {
		if(envName==null) {
			System.out.println("Running test cases on QA environment since user did not pass the environment name");
			
			ip=new FileInputStream("./src/test/resources/Config/qa.config.properties");
			prop.load(ip);
		}
		
		else {
			
			switch (envName.toLowerCase()) {
			
			case "qa":
				System.out.println("Running test cases on the QA environment");
				ip=new FileInputStream("./src/test/resources/Config/qa.config.properties");
				prop.load(ip);
                break;
                
              
			case "stage":
				System.out.println("Running test cases on the Stage environment");
				ip=new FileInputStream("./src/test/resources/Config/stage.config.properties");
				prop.load(ip);
                break;
                
			case "prod":
				System.out.println("Running test cases on the Prod environment");
				ip=new FileInputStream("./src/test/resources/Config/config.properties");
				prop.load(ip);
                break;
                	
			default:
				System.out.println("Please pass valid environment details.Terminating the test execution");
				throw new Exception("Incorrect Environment Details");
				
			}
			
			
		}
		
		}
		
	catch (FileNotFoundException e) {
		e.printStackTrace();
		
	}
	catch(IOException e){
		e.printStackTrace();
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		return prop;
}
}