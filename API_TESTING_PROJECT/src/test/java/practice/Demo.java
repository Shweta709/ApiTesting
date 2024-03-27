package practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.payLoad;

public class Demo {

	public static void main(String[] args) {
		
		// validate if Add Place API is workimg as expected 
				//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
				
				//given - all input details 
				//when - Submit the API -resource,http method
				//Then - validate the response
		
		
		// validate if Add Place API is workimg as expected
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Conten-Type","application/json")
		.body(payLoad.Payload())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //parsing the string
		String PlaceId=js.getString("place_id");
		System.out.println(PlaceId);
		
		//Update Place
		String newAddress="Summer Walk Africa";
		
		//Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+PlaceId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
			
		
		
		//get Place
		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id",PlaceId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
			JsonPath js1=new JsonPath(getPlaceResponse);
			String actualAddress =js1.getString("address");
			System.out.println(actualAddress);
			Assert.assertEquals(actualAddress, "Summer Walk Africa");
		
	}

}
