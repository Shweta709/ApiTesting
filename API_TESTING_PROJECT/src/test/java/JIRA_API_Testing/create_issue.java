package JIRA_API_Testing;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class create_issue {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session=new SessionFilter();
		given().header("Content-Type","application/json")
		.body("{ \"username\": \"shwetarajad553\", \"password\": \"Shweta@123\" }").filter(session)
		.when().post("/rest/auth/1/session").then().extract().response().asString();
		
		//create A issue
		/*
		given().header("Content-type","application/json").header("Cookie","JSESSIONID=D98BD44624E28A21599DBB709887A48F")
		.log().all().body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": \r\n"
				+ "        {\r\n"
				+ "            \"key\": \"AP\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"One Issue is created\",\r\n"
				+ "        \"description\": \"One bug is creating\",\r\n"
				+ "        \"issuetype\":{\r\n"
				+ "            \"name\":\"Bug\"\r\n"
				+ "        } \r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue").then().log().all().assertThat().statusCode(201);
		
		
		/*
		//delete a issue
		given().header("Content-type","application/json").filter(session)
		.when().delete("rest/api/2/issue/10203").then().log().all().assertThat().statusCode(204);
		*/
		
		//delete a issue
	/*
		given().header("content-type","application/json").filter(session).when().delete("rest/api/2/issue/10206")
		.then().assertThat().statusCode(204);
		
		*/
		
		String addComent="This is my updated comment";
		//Add comment
		/*
		String addComent="This is my 3rd comment";
		String res1=given().pathParam("id","10208").header("content-type","application/json").log().all()
		.body("{\r\n"
				+ "    \"body\": \""+addComent+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue/{id}/comment").then().log().all()
		.assertThat().statusCode(201).extract().response()
		.asString();
		
		JsonPath js=new JsonPath(res1);
		String id=js.get("id");
		System.out.println(id);
		*/
		
		
		//update the issue
		given().header("content-type","application/json").queryParam("expand","body")
		.body("{\r\n"
				+ "    \"body\": \""+addComent+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().put("/rest/api/2/issue/10208/comment/10100").then().assertThat().statusCode(200);
		//get issue 
		String res=given().header("content-type","application/json").pathParam("id","10208")
			.queryParam("fields","comment").filter(session)
		.when().get("rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		
		

	}

}
