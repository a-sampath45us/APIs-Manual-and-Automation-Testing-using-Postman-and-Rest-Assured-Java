import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import files.ReusableMethods;

public class OAuthAPITesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Authorization Server API 
		//Post - Send Client Credentials Response Access Token
		//Get - Send Access Token Response Course Details
		
		String response = given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String at = js.get("access_token");
		
		given()
		.queryParam("access_token", at)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all();

	}

}
