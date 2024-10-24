import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import files.ReusableMethods;

public class JiraAPITesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Using Rest APIs to create Bug and attach files in Jira (UI Automation)
		
		//Creating Bug in Jira
		RestAssured.baseURI = "https://rajasampathkumar.atlassian.net/";
		
		String createIssueResponse = given()
		.header("Content-Type", "application/json")
		.header("Authorization","Basic YS5zYW1wYXRoNDV1c0BnbWFpbC5jb206QVRBVFQzeEZmR0YwLVdkUExCRVowXzk1WHNXSDY2dTF5VnhDekNyUzF3ZEVuQXYtX2VMTUFLVHJCbHBKZmVHSHFqYlFQS2s3QlBaY2tsWkRnZGU4NFRfR3hxdHFMc1ExMi1lajh2Q2E0d2VzOVRiSmIzLVNVaFBtQ0NWaXMzNFZDS3VPSjFoQ1ZOSG5uNFpLZzh0VVlUQ3JIb2lDTTg0Rk93TEgwWGJDQl9xTmdWR0VlZlBHQjNRPTU2MUJDQUE4")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"reporter\": {\r\n"
				+ "      \"id\": \"63f915ad3ec8aa51d3d373d0\"\r\n"
				+ "    },\r\n"
				+ "       \"summary\": \"Links are not working\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.when().post("rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(createIssueResponse);
		String issueId = js.get("id");
		
		//Adding attachment to the created bug
		given()
		.pathParam("key", issueId)
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization","Basic YS5zYW1wYXRoNDV1c0BnbWFpbC5jb206QVRBVFQzeEZmR0YwLVdkUExCRVowXzk1WHNXSDY2dTF5VnhDekNyUzF3ZEVuQXYtX2VMTUFLVHJCbHBKZmVHSHFqYlFQS2s3QlBaY2tsWkRnZGU4NFRfR3hxdHFMc1ExMi1lajh2Q2E0d2VzOVRiSmIzLVNVaFBtQ0NWaXMzNFZDS3VPSjFoQ1ZOSG5uNFpLZzh0VVlUQ3JIb2lDTTg0Rk93TEgwWGJDQl9xTmdWR0VlZlBHQjNRPTU2MUJDQUE4")
		.multiPart("file",new File("D:\\Java Selenium\\API Testing\\API Contracts\\Library-API.docx"))
		.log().all()
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
