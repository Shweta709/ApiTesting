package pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class Deserialization {

	public static void main(String[] args) {

		
		String response = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type","client_credentials").formParam("scope","trust").log().all().when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		JsonPath js=new JsonPath(response);
		String token=js.get("access_token");
		System.out.println(token);
		
		
		//here i am creating object of POJO class
		GetCourse gc=given().queryParam("access_token",token).when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
	
		//With the help of Object we can directly call the Getter Method present in GETCOURSE CLASS
		System.out.println(gc.getUrl());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		//HERE i am getting through INDEX
		String res=gc.getCourses().getApi().get(0).getCourseTitle();
		System.out.println(res);
		
		//Here retire data using LIST and store the element of GETAPI in THAT list
		List<Api> Apicourse=gc.getCourses().getApi();
		for(int i=0;i<Apicourse.size();i++) {
			if(Apicourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(Apicourse.get(i).getPrice());
			}
		}
		//Here retire data using LIST and store the element of GetAutomation  in THAT list
		List<WebAutomation> WebAutomationcourse=gc.getCourses().getWebAutomation();
		for(int i=0;i<WebAutomationcourse.size();i++) {
			System.out.println(WebAutomationcourse.get(i).getCourseTitle());
		}
		
		//Creating String type Array For comparison
		String[] a= {"Selenium Webdriver Java","Cypress","Protractor"};
	ArrayList<String> b=new ArrayList<String>();
	for(int i=0;i<WebAutomationcourse.size();i++) {
		b.add(WebAutomationcourse.get(i).getCourseTitle());
	}
	for(String arr:b) {
		System.out.println(arr);
	}
	//Here Converting String type array to List
	List<String> expectedList=Arrays.asList(a);
	Assert.assertTrue(b.equals(expectedList));
	
		
		

	}

}
