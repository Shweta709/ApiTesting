package googleMapApiTesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

import org.testng.Assert;

public class GoogleMapApiTesting {

	public static void main(String[] args) {
		
		
		//Google Maps Add API (POST):
			//This API Will add new place into Server

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res=given().log().all().queryParam("key","qaclick123").header("Content-type","application/json")
				.body("{\r\n"
						+ "  \"location\": {\r\n"
						+ "    \"lat\": -38.383494,\r\n"
						+ "    \"lng\": 33.427362\r\n"
						+ "  },\r\n"
						+ "  \"accuracy\": 50,\r\n"
						+ "  \"name\": \"Frontline house\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
						+ "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n"
						+ "    \"shop\"\r\n"
						+ "  ],\r\n"
						+ "  \"website\": \"http://google.com\",\r\n"
						+ "  \"language\": \"French-IN\"\r\n"
						+ "}\r\n"
						+ "")
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
		
		System.out.println(res);
		JsonPath js=new JsonPath(res); //parsing the string
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		//Google Maps get Place  API   (GET):
			//This API Will get existing place details from Server
		
		String response1=given().log().all().queryParam("key", "qaclick123").queryParam("placeid","place_id")
				.when().get("/maps/api/place/get/json")
				.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response1);
		JsonPath js1=new JsonPath(response1);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress,"29, side layout, cohen 09");
		
		
		

	}

}
