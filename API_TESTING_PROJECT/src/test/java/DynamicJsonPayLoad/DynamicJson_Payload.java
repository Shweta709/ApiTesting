package DynamicJsonPayLoad;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson_Payload {
	
	
	public static void main(String[] args) {
	
		
		RestAssured.baseURI="http://216.10.245.166/";
		String repnse=given().log().all().header("","").
		body(Json_Payload1.addBook())
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().toString();
		
	JsonPath js=new JsonPath(repnse);
	String id=js.getString("ID");
	System.out.println(id);
	
	
	}

}
