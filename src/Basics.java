import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.PayLoadd;
import files.ReusableMethods;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		//Validate if Add Place API is working as expected
		//Rest Assured works on 3 principles used in every automation test
		//given - all input details
		//when - submit the API(resource & http method)
		//then - validate the response
		
		//Google Maps API Testing
		//Add Place -> Update Place with New Address -> Get Place to Validate if New Address is present in response
		//Add Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(PayLoadd.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		//System.out.println(response);
		
		JsonPath js = ReusableMethods.rawToJson(response); //This class take string as input give output json //object parse the converted json //this class is for parsing Json
		String placeId = js.getString("place_id");
		
		//System.out.println(placeId);
		
		//Update Place
		
		String newAddress = "70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"))
		.header("server", "Apache/2.4.52 (Ubuntu)");
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	     
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		
		//we can compare actual & expected address in 2 ways. 1 is using built in method of Rest Assured called body (.body("address", equalTo(newAddress)) put this beside statusCode in Get Place
		//2 is using JsonPath Class & Assertion class.
		
		Assert.assertEquals(actualAddress, newAddress);
		
		//How to call Json file from local folder to project
		//we can call json payload using 3 ways
		//1 is directly putting in the .body("here")
		//2 is create a static method in separate class put payload in that method & call that method in .body(className.methodName) like this
		//3 is convert the content of file into String (content->byte->string) call json file from local folder to project shown below
		//.body(new String(Files.readAllBytes(Paths.get("D:\Java Selenium\API Testing\API Contracts\Library+API.postman_collection.json"))))
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
