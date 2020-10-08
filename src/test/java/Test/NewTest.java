package Test;

import org.testng.annotations.Test;

import com.learning.api.Files.Payload;
import com.learning.api.Utils.FrameworkProperties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;

public class NewTest {
  @Test(dataProvider="UsersData")
  public void addUser(String Name, String occupation) {
	  
	  String base_url = FrameworkProperties.get("BASE_URL");
	  System.out.println(base_url);
	  RestAssured.baseURI = "https://reqres.in";
	  JsonPath js = given().header("Content-Type","application/json").
	  body(Payload.addUser()).when()
	  .post("/api/users").
	  then().assertThat().statusCode(201).extract().jsonPath();
	  
	  String id = js.get("id");
	  System.out.println("id is "+ id);
	  System.out.println(Name);
	  System.out.println(occupation);
	  
	  
  }
  
  @DataProvider(name = "UsersData")
  public Object[][] getData(){
	return new Object[][] {{"prabhat","student"},{"laxman","employee"}};
	  
  }

 
}
