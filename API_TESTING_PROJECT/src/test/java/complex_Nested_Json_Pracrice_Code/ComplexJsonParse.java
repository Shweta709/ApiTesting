package complex_Nested_Json_Pracrice_Code;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
	
		
		JsonPath js=new JsonPath(Json_PayLoad.Complex_Json());
		int count=js.getInt("courses.size()");
		System.out.println(count);
		/*
		 Print No of courses returned by API

		 2.Print Purchase Amount

		 3. Print Title of the first course

		 4. Print All course titles and their respective Prices

		 5. Print no of copies sold by RPA Course

		 6. Verify if Sum of all Course prices matches with Purchase Amount
		 */
		
		//Print Purchase Amount
		System.out.println("Print Purchase Amount");
		System.out.println(js.get("dashboard.purchaseAmount").toString());
		
		System.out.println("Print Title of the first course");
		//Print Title of the first course
	
		System.out.println(js.get("courses[0].title").toString());
		
		//Print All course titles and their respective Prices
		System.out.println("Print All course titles and their respective Prices");
		for(int i=0;i<count;i++) {
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.get("courses["+i+"].price").toString());
			
		}
		
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++) {
			
			String title=js.get("courses["+i+"].title");
			if(title.equalsIgnoreCase("PRA"))
			{
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}
		System.out.println("Sum of all Course prices");
		int sum1=0;
		for(int i=0;i<count;i++) {
			
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int totalprice=price*copies;
			sum1 =sum1+totalprice;
			
		}
		System.out.println(sum1);
		int purchaseAmout=js.getInt("dashboard.purchaseAmount");
		if(sum1==purchaseAmout) {
			System.out.println("pass");
		}
		
	
		
		
		
		
	
	}
	
}
