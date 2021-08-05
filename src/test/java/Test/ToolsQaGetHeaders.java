package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ToolsQaGetHeaders {
	@Test
	public void validateHeader() {
	RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
	 RequestSpecification httpRequest = RestAssured.given();
	 Response response = httpRequest.get("/Hyderabad");
	 
	 // Reader header of a give name. In this line we will get
	 // Header named Content-Type
	 String contentType = response.header("Content-Type");
	 Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);
	 
	 // Reader header of a give name. In this line we will get
	 // Header named Server
	 String serverType =  response.header("Server");
	 Assert.assertEquals(serverType /* actual value */, "nginx/1.12.1" /* expected value */);
	 
	 // Reader header of a give name. In this line we will get
	 // Header named Content-Encoding
	 String contentEncoding = response.header("Content-Encoding");
	 Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
	}
}



