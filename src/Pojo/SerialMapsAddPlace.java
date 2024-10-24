package Pojo;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerialMapsAddPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Serialization of Google Maps API Add Place
		//Serialization - JavaObject ---> Json Body Req Payload at runtime
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
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
		
		String resp = given().log().all().queryParam("key", "qaclick123")
		.body(ap)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(resp);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
