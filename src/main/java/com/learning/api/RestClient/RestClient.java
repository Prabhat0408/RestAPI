package com.learning.api.RestClient;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestClient {
	
	public static Response get(String Resource){
		 //RestAssured.baseURI = base_url;
		Response rs = given().when().get(Resource);
		return rs;	
	}

}
