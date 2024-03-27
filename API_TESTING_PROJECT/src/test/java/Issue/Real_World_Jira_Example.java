package Issue;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.junit.Assert;

public class Real_World_Jira_Example {
	
	public static void main(String[] args) {
		
		//login to JIRA TO CREATE SESSION using Login API
		//Add comment to existing issue to ADD COMMENT API
		
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session=new SessionFilter();
		given().header("Content-Type","application/json")
		.body("{ \"username\": \"shwetarajad553\", \"password\": \"Shweta@123\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
	
		//add comment
		String actualMessage="Hi My name Is Shweta Pradeep";
		String currentId=given().pathParam("key","10110").log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \""+actualMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201).extract().response().asString();		
		
		
		JsonPath js=new JsonPath(currentId);
		String id=js.get("id");
		System.out.println(id);
		
		//ADD Attachment
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key","10110")
		.header("Content-Type","multipart/form-data").multiPart("file",new File("C:\\Users\\NIhaL2310\\eclipse-workspace\\API_TESTING_PROJECT\\src\\test\\java\\Issue\\File.txt"))
		.when().post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);	
		
		//get issue
		
		String response=given().pathParam("key","10110")
				.queryParam("fields","comment").filter(session).when().get("rest/api/2/issue/{key}").then().log().all()
		.extract().response().asString();
		//System.out.println(response);
		JsonPath js1=new JsonPath(response);
		int IDcount=js1.getInt("fields.comment.comments.size()");
		for(int i=0;i<IDcount;i++) {
			String commentsId=js1.get("fields.comment.comments["+i+"].id");
			if(commentsId.equalsIgnoreCase(id))
			{
				String expectedMessage=js1.get("fields.comment.comments["+i+"].body");
				System.out.println(expectedMessage);
				Assert.assertEquals(actualMessage, expectedMessage);
			}
		}	
		
	}

}
