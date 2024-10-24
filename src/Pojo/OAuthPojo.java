package Pojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import files.ReusableMethods;

public class OAuthPojo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Authorization Server API 
		//Post - Send Client Credentials Response Access Token
		//Get - Send Access Token Response Course Details
		
		String[] ct = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String response = given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String at = js.get("access_token");
		
		GetCourseRespPojo gc = given()
		.queryParam("access_token", at)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetCourseRespPojo.class);
		
		System.out.println(gc.getLinkedin());
		System.out.println(gc.getInstructor());
		
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle()); //Soap UI course title
		List<api> apiCourses = gc.getCourses().getApi(); //soap ui course title price
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		//print all course titles of webAutomation
		ArrayList<String> al = new ArrayList<String>();
		List<webAutomation> webCourses = gc.getCourses().getWebAutomation();
		/*for(int i=0;i<webCourses.size();i++)
		{
			System.out.println(webCourses.get(i).getCourseTitle());
		}*/
		
		for(int i=0;i<webCourses.size();i++)
		{
			al.add(webCourses.get(i).getCourseTitle());
		}
		
		List<String> el = Arrays.asList(ct);
		
		Assert.assertTrue(al.equals(el));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
