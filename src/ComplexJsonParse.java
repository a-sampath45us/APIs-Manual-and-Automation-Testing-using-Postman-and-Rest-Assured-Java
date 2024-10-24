import org.testng.Assert;

import files.PayLoadd;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Parsing the Complex Json Body 
		JsonPath js = new JsonPath(PayLoadd.CoursePrice());
		
		//print no of courses returned by API
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//print purchase amount 
		
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//print title of first course
		
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		
		//print all course titles and their respective prices
		
		for(int i=0;i<count;i++)
		{
			 String courseTitles = js.get("courses["+i+"].title");
			 System.out.println(courseTitles);
			 System.out.println(js.getInt("courses["+i+"].price"));
			
		}
		
		//print no of copies sold by RPA Course
		
		System.out.println("print no of copies sold by RPA Course");
		
		for(int i=0;i<count;i++)
		{
			 String courseTitles = js.get("courses["+i+"].title");
			 if(courseTitles.equalsIgnoreCase("RPA"))
			 {
				System.out.println(js.getInt("courses["+i+"].copies"));
				break;
			 }
		
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		int totPrice = 0;
		for(int i=0;i<count;i++)
		{
	
			int noOfCopies = js.getInt("courses["+i+"].copies");
			int priceOfCourse = js.getInt("courses["+i+"].price");
			int Price = noOfCopies*priceOfCourse;
			totPrice = Price + totPrice;
		}
		System.out.println(totPrice);
		Assert.assertEquals(totPrice, totalAmount);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
