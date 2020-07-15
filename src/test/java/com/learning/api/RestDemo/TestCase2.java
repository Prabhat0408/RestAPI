package com.learning.api.RestDemo;

import org.testng.annotations.Test;

import com.learning.api.Files.Payload;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class TestCase2 {
  @Test
  public void f() {
	  RestAssured.baseURI = "https://rahulshettyacademy.com";
	  given().log().all().queryParam("key", "qaclick123")
			 .header("Content-Type","application/json")
			 .body(Payload.addPlace()).when().post("/maps/api/place/add/json").then()
			 .log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
			 .header("Server", "Apache/2.4.18 (Ubuntu)");
}
  
//  @Test
//  public void testDemoQA() {
//	  RestAssured.baseURI = "https://restapi.demoqa.com";
//	  given().log().all().when().get("/utilities/weather/city/Hyderabad")
//	  .then().log().all().assertThat().statusCode(200);
//  }
}
