package com.qa.gorest.Utils;

public class StringUtils {

	
	public static String generateRandomEmailId() {
		
		return "api"+System.currentTimeMillis()+"@api.com";
		
	}
	
public static String generateRandomName() {
		
		return "APIAutomationUser"+System.currentTimeMillis();
		
	}
	
}
