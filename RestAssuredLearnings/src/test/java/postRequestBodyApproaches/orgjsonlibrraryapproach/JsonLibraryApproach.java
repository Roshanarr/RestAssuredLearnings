package postRequestBodyApproaches.orgjsonlibrraryapproach;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class JsonLibraryApproach {
	String studentId1;
	@Test
	void createStudentsUsingJSONLibrary() {
		
		JSONObject requestBody1= new JSONObject();
		requestBody1.put("name","ram");
		requestBody1.put("location","India");
		requestBody1.put("phone","342234444");
		String courses[]= {"Playwright","JS"};
		requestBody1.put("courses", courses);
		
		studentId1=given()
				.contentType("application/json")
				.body(requestBody1.toString())
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("ram"))
				.body("location", equalTo("India"))
				.body("phone", equalTo("342234444"))
				.body("courses[0]",equalTo("Playwright"))
				.body("courses[1]",equalTo("JS"))
				.header("Content-Type", equalTo("application/json"))
				.log().all()
				.extract().jsonPath().getString("id");
				
			
		}
	@AfterMethod
	void DeleteUserusingJSONLibrary() {
		given()
		.when()
			.delete("http://localhost:3000/students/" +studentId1)
		.then()
			.statusCode(200);
	}
	}


