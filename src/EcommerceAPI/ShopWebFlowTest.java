package EcommerceAPI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ShopWebFlowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//API Automation Testing of End to End Business Flow (Shopping Website)
		
		//Login API - After logging in with valid credentials Login API generates unique user id and an access token
		//Logging in - is Authentication - Who you are? 
		//Login API generates unique user id and access token - Authorization - What you are allowed to do and see?
		//Backend Process - Login API sends HTTP Post method with form parameters(grant types - client credentials/password type/Authorization Code) to OAuth Server
		//OAuth Server validates data and generates access token. Login API gets this Access token. Using this access token Login API/other APIs makes subsequent calls to other APIs
		//Other APIs unlock after receiving access token and gives access to their data
		
		//Login API -> Create Product -> Purchase Order on Created Product -> Delete Product
		
		//Login
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LoginPojo loginReq = new LoginPojo();
		loginReq.setUserEmail("samusa@gmail.com");
		loginReq.setUserPassword("12345678Sa*");
		
		//HTTPS/SSL certifications .relaxedHTTPSValidation method(after given()) - to bypass invalid certifications
		//that means we'll trust all hosts regardless if the SSL Certi is invalid
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginReq);		
		LoginRespPojo loginResp = reqLogin.when().post("/api/ecom/auth/login")
		.then().extract().response().as(LoginRespPojo.class);
		
		String token = loginResp.getToken();
	    String userId = loginResp.getUserId();
		
		//Create Product
		
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
		.param("productName", "lion")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productPrice", "11500")
		.param("productDescription", "Addias Originals")
		.param("productFor", "men")
		.multiPart("productImage", new File("D:\\OneDrive - University of North Alabama\\Desktop\\images (4).jpeg"));
		
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");
		
		//Create Order
		
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.setContentType(ContentType.JSON)
				.build();
		
		OrderDetail ord = new OrderDetail();
		ord.setCountry("India");
		ord.setProductOrderedId(productId);
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(ord);
		
		Orders or = new Orders();
		or.setOrders(orderDetailList);
		
		
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(or);
		
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order")
		.then().log().all().extract().response().asString();
		
		//Delete Order
		
		//Query Parameter - ? & Key-Value Pair - present in End URI
		//Path Parameter - / like /api/ecom/product/delete-product/productid - In this productId is path parameter and its value like 66fbbbb2ae2afd4c0b8b197a - present in End URI
		//Form Parameter - txt or file - present in body req payload(form-data)
		
		RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.setContentType(ContentType.JSON)
				.build();
		
		RequestSpecification deleteProdReq = given().log().all().spec(deleteProdBaseReq).pathParam("productId", productId);
		
		String deleteResp = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteResp);
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
		
	}

}
