package com.learning.api.RestDemo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.learning.api.Files.JsonWrapperObj;
import com.learning.api.Files.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class RestAssuredFirstClassTest {
  @Test
  public void validatePostPustGet() {
	  RestAssured.baseURI = "https://rahulshettyacademy.com";
	  String response = given().queryParam("key", "qaclick123")
			 .header("Content-Type","application/json")
			 .body(Payload.addPlace()).when().post("/maps/api/place/add/json").then()
			 .assertThat().statusCode(200).body("scope", equalTo("APP"))
			 .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	  
	  System.out.println(response);
	  
	  JsonPath js =JsonWrapperObj.getObj(response);
	  String placeId= js.get("place_id");
	  System.out.println("Place is is "+placeId);
	  
	  String old_Add= js.get("address");
	  
	  String new_Add = "Vadodra Steet";
	  
	  given().log().all().queryParam("key", "qaclick123")
		 .header("Content-Type","application/json")
		 .body("{\r\n" + 
					"\"place_id\":\""+placeId+"\",\r\n" + 
					"\"address\":\""+new_Add+"\",\r\n" + 
					"\"key\":\"qaclick123\"\r\n" + 
					"}").when().put("maps/api/place/update/json").then().assertThat().log().all()
		 .statusCode(200).body("msg", equalTo("Address successfully updated"));;
	  
	  
	  String response_AfterUpdate= given().log().all().queryParam("key", "qaclick123")
			  .queryParam("place_id",placeId)
			  .when().get("/maps/api/place/get/json")
			  .then().log().all().assertThat().statusCode(200).extract().asString();
	  
	  JsonPath js1 = JsonWrapperObj.getObj(response_AfterUpdate);
	  System.out.println("updated addressis "+js1.get("address"));
	  
	  Assert.assertEquals(new_Add, js1.get("address"));
	
	  
	
  }
 
}
