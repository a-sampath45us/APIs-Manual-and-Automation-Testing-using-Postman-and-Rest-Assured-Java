
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlaceReqBodyPojo;
import Pojo.location;

public class SpecBuildTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Request Specific Builder & Response Specific Builder
		//In Specific Build we can set & add commonly used things from req & response
		
		
		AddPlaceReqBodyPojo ap = new AddPlaceReqBodyPojo(); //created object for POJO Class to implement those methods in this class(using it in body(req payload))
		ap.setAccuracy(50);
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		location lc = new location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		ap.setLocation(lc);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification resp = given().spec(req).body(ap);
		
		Response response = resp.when().post("/maps/api/place/add/json")
		.then().spec(resspec).extract().response();
		
		String respString = response.asString();
		System.out.println(respString);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
