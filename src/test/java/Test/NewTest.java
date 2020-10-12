package Test;

import org.testng.annotations.Test;

import com.learning.api.Files.Payload;
import com.learning.api.RestClient.RestClient;
import com.learning.api.Utils.ConfigProperties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class NewTest {
	private static String base_url;
	private Response rs;
	@BeforeClass
	public void beforeClass() {
		base_url = ConfigProperties.get("BASE_URL");
	}
	
  @Test
  public void addUser() {
	  
		/* simple plain code
		 * String base_url = ConfigProperties.get("BASE_URL");
		 * System.out.println(base_url); RestAssured.baseURI = "https://reqres.in";
		 * JsonPath js = given().header("Content-Type","application/json").
		 * body(Payload.addUser()).when() .post("/api/users").
		 * then().assertThat().statusCode(201).extract().jsonPath();
		 */
	  
	  //new approach from rest client using given,when and then
	  RestAssured.baseURI = base_url;
	  rs = RestClient.get("/api/users");
	  assertEquals(rs.statusCode(),200);
	  
	  String responseBody= rs.getBody().asString();
	  
	  JsonPath js = new JsonPath(responseBody);
	  System.out.println(js.get("page"));
	  System.out.println(js.get("data[0]"));
	  
	  //String id = js.get("id");
	  //System.out.println("id is "+ id);
	 
	  
	  
  }
  
  @DataProvider(name = "UsersData")
  public Object[][] getData(){
	return new Object[][] {{"prabhat","student"},{"laxman","employee"}};
	  
  }

 
}
