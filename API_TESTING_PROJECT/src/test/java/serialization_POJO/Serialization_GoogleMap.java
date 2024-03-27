package serialization_POJO;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class Serialization_GoogleMap {
	
	@Test
	public void GoogleMap() {
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
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=
		given().queryParam("key","qaclick123").header("Content-type","application/json").body(add)
		.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().asString();
		
		System.out.println(response);
	}
}
