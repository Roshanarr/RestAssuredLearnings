package postRequestBodyApproaches.UsingExternalJSON;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class UsingExternalJSONDemo {
	String studentId3;
	@Test
	void createStudentsUsingExternalJson() throws FileNotFoundException {
		
	
		File myfile= new File(".\\src\\test\\java\\postRequestBodyApproaches\\UsingExternalJSON\\studenst3.json");
		FileReader filereader= new FileReader(myfile);
		JSONTokener jsonTokener = new JSONTokener(filereader);
		
		JSONObject requestBody3= new JSONObject(jsonTokener);
		
		studentId3=given()
				.contentType("application/json")
				.body(requestBody3.toString())
				.when()
				.post("http://localhost:3000/students")
				.then()
				.statusCode(201)
				.body("name", equalTo("ramesh"))
				.body("location", equalTo("Denmark"))
				.body("phone", equalTo("1234567890"))
				.body("courses[0]",equalTo("Cypress"))
				.body("courses[1]",equalTo("TS"))
				.header("Content-Type", equalTo("application/json"))
				.log().all()
				.extract().jsonPath().getString("id");
				
	}
	
	@AfterMethod
	void DeleteUserusingEXternalJSON() {
		given()
		.when()
			.delete("http://localhost:3000/students/" +studentId3)
		.then()
			.statusCode(200);
		
}
}
