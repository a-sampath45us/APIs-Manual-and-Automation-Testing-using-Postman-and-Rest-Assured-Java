package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	//Library API Testing
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type", "application/json")
		.body(PayLoadd.Addbook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		
		return new Object[][] {{"hkjd","4565"},{"hdsjd","8454"},{"jsnk","8752"}};
		
	}
	

}
