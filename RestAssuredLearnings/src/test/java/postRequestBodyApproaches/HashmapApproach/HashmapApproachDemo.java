package postRequestBodyApproaches.HashmapApproach;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HashmapApproachDemo {
	String studentId;
	@Test
	void CreateStudentsUsingHashmap(){
		//we are using Object as we can store any type of data 
		HashMap<String,Object> RequestBody = new HashMap<String,Object>();
		RequestBody.put("name","Scott");
		RequestBody.put("location","France");
		RequestBody.put("phone","34223444");
		String courses[]= {"c","c++"};
		RequestBody.put("courses", courses);
		
		
		
		studentId=given()
			.contentType("application/json")
			.body(RequestBody)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("34223444"))
			.header("Content-Type", equalTo("application/json"))
			.log().all()
			.extract().jsonPath().getString("id");
			
		
	}
	@AfterMethod
	void DeleteUser() {
		given()
		.when()
			.delete("http://localhost:3000/students/" +studentId)
		.then()
			.statusCode(200);
	}

}
