package com.qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.exceptions.APIFrameworkExceptions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAuthorizationAdded=false;
	
	
	
	
	//private static final String BASE_URI="https://gorest.co.in";
	//private static final String BEARER_TOKEN="143dfa8a8f25bf22b7467cfe874d23897f551f1125224d9c73283cc9f5643a50";
	
	public RestClient(Properties prop,String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.baseURI=baseURI;
		this.prop=prop;
		
	}
	
	
	public void addAuthorization() {
		if(!isAuthorizationAdded)
		specBuilder.addHeader("Authorization", "Bearer "+prop.getProperty("tokenId"));
		isAuthorizationAdded=true;
	}
	
	
	public void addContentType(String contentType) {
		
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
			
		case "xml":
		    specBuilder.setContentType(ContentType.XML);
			break;
			
			
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;

			
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("Please enter correct content type");
			throw new APIFrameworkExceptions("INVALIDCONTENTTYPE");
			
		}
	}
	
	
	
	
	private RequestSpecification reqSpecBuilder(Boolean addAuth) {
		specBuilder.setBaseUri(baseURI);
		 if(addAuth){
				addAuthorization();
			    }
		return specBuilder.build();
		
	}
	
	
	private RequestSpecification reqSpecBuilder(Boolean addAuth,Map<String,String> headersMap) {
	    specBuilder.setBaseUri(baseURI);
	    
	    if(addAuth){
		addAuthorization();
	    }
		if(headersMap!=null) {
		specBuilder.addHeaders(headersMap);
		}
        return specBuilder.build();		
		
	}
	
	
	private RequestSpecification reqSpecBuilder(Boolean addAuth,Map<String,String> headersMap, Map<String,Object> queryParamsMap) {
	    specBuilder.setBaseUri(baseURI);
	    if(addAuth){
			addAuthorization();
		    }
		if(headersMap!=null) {
		specBuilder.addHeaders(headersMap);
		}
		if(queryParamsMap!=null) {
		specBuilder.addQueryParams(queryParamsMap);
		}
		return specBuilder.build();
	}
	
   private RequestSpecification reqSpecBuilder(Boolean addAuth,Object requestBody,String contentType) {
	   specBuilder.setBaseUri(baseURI);
	   if(addAuth){
			addAuthorization();
		    }
	   addContentType(contentType);
	   if(requestBody!=null) {
	   specBuilder.setBody(requestBody);
	   }
	   return specBuilder.build();
	   
	}	
	
	private RequestSpecification reqSpecBuilder(Boolean addAuth,Object requestBody,String contentType,Map<String,String> headersMap) {
	   specBuilder.setBaseUri(baseURI);
	   if(addAuth){
			addAuthorization();
		    }
	   addContentType(contentType);
	   
	   if(requestBody!=null) {
	   specBuilder.setBody(requestBody);
	   }
	   
	   if(headersMap!=null) {
		   specBuilder.addHeaders(headersMap);
	   }
	   
	   return specBuilder.build();
	   
	}	
	
	
	//HTTP Method Utils
	
	
	public Response get(String serviceurl,boolean addAuth,boolean log) {
		
		if(log) {
		return RestAssured.given(reqSpecBuilder(addAuth)).log().all()
		.when()
		.get(serviceurl);
		}
		
		else {
			return RestAssured.given(reqSpecBuilder(addAuth))
					.when()
					.get(serviceurl);
		
		}
		
	}
		public Response get(String serviceurl,Boolean addAuth,Map<String,String> headersMap,boolean log) {
			
			
			if(log) {
				return RestAssured.given(reqSpecBuilder(addAuth,headersMap)).log().all()
				.when()
				.get(serviceurl);
			}
			else {
				
				return RestAssured.given(reqSpecBuilder(addAuth,headersMap))
						.when()
						.get(serviceurl);
				
			}
			
		}
		
	
	public Response get(String serviceurl,Boolean addAuth,Map<String,String> headersMap,Map<String,Object> queryParams,boolean log) {
		
			if(log) {
				return RestAssured.given(reqSpecBuilder(addAuth,headersMap, queryParams)).log().all()
				.when()
				.get(serviceurl);
			}
			else {
				
				return RestAssured.given(reqSpecBuilder(addAuth,headersMap, queryParams))
						.when()
						.get(serviceurl);
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		public Response post(String serviceurl,Boolean addAuth,Object requestBody,String contenttype,boolean log) {
			
			if(log) {
				return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype)).log().all()
						.when()
						.post(serviceurl);
}
			
			else {
				return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype))
						.when()
						.post(serviceurl);
				
			}
			
			
		}
		
		
		
		public Response post(String serviceurl,Boolean addAuth,Object requestBody,Map<String,String> headersMap,boolean log) {
			
			if(log) {
				return RestAssured.given(reqSpecBuilder(addAuth,requestBody, serviceurl, headersMap)).log().all()
				.when()
				.post(serviceurl);
				
			}
			else {
				
				return RestAssured.given(reqSpecBuilder(addAuth,requestBody, serviceurl, headersMap))
						.when()
						.post(serviceurl);
						
			}
			
		}
		
		
		public Response put(String serviceurl,Boolean addAuth,Object requestBody,String contenttype,boolean log) {
			
			if(log) {
			return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype)).log().all()
			.when()
			.put(serviceurl);
			}
			
			else {
				return RestAssured.given()
						.when()
						.put(serviceurl);
				
			}
			
		}
		
		
public Response put(String serviceurl,Boolean addAuth,Object requestBody,String contenttype,Map<String,String> headersMap,boolean log) {
			
			if(log) {
			return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype, headersMap)).log().all()
			.when()
			.put(serviceurl);
			}
			
			else {
				return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype, headersMap))
						.when()
						.put(serviceurl);
				
			}
			
		}
		
		

public Response patch(String serviceurl,Boolean addAuth,Object requestBody,String contenttype,boolean log) {
	
	if(log) {
	return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype)).log().all()
	.when()
	.patch(serviceurl);
	}
	
	else {
		return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype))
				.when()
				.patch(serviceurl);
		
	}
	
}


public Response patch(String serviceurl,Boolean addAuth,Object requestBody,String contenttype,Map<String,String> headersMap,boolean log) {
	
	if(log) {
	return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype, headersMap)).log().all()
	.when()
	.patch(serviceurl);
	}
	
	else {
		return RestAssured.given(reqSpecBuilder(addAuth,requestBody, contenttype, headersMap))
				.when()
				.patch(serviceurl);
		
	}
	
}

           public Response delete(String serviceurl,Boolean addAuth,boolean log) {
        	   
               if(addAuth) {
            	 addAuthorization();
               }
        	   
        	   if(log) {
        		   return RestAssured.given().log().all()
        				   .when()
        				   .delete(serviceurl);
        		   
        	   }
        	   else {
        		   return RestAssured.given()
        				   .when()
        				   .delete(serviceurl);
        		   
        		   
        	   }
        	   
        	   
           }
		
		
           public String generateAccessToken(String baseURI,String serviceurl,String grantType,String client_id,String client_secret) {
       		
       		RestAssured.baseURI=baseURI;
       		 String accesstoken=given()
       		.contentType("application/x-www-form-urlencoded")
       		.formParam("grant_type", grantType)
       		.formParam("client_id", client_id)
       		.formParam("client_secret", client_secret)
       		.when()
       		.post(serviceurl)
       		.then()
       		.extract()
       		.path("access_token");
       		
       		return accesstoken;
       		
       	}
           
           
           
           
           
	}
	
	
	
	
	
	

