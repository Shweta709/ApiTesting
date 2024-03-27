
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serialization_POJO.GoogleMap_Add_Place;
import serialization_POJO.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class Spec_Builder {
	
	@Test
	public void Request_Builder() {
		GoogleMap_Add_Place add=new GoogleMap_Add_Place();
		add.setAccuracy(50);
		add.setName("Frontline house");
		add.setPhone_number("(+91) 983 893 3937");
		add.setWebsite("http://google.com");
		add.setLanguage("French-IN");
		List<String> type=new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		add.setTypes(type);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		add.setLocation(l);
		
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
	.setContentType(ContentType.JSON).build();
		
	ResponseSpecification respon=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	RequestSpecification res=given().spec(req).body(add);
		
			Response response=res.when().post("/maps/api/place/add/json").then()
					.spec(respon).extract().response();
	/*
	 * //RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=
		given().spec(req).body(add)
		.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().asString();
		*/
			String respinseString=response.asString();
		
		System.out.println(respinseString);
	}
}
