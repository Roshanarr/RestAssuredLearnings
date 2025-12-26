package postRequestBodyApproaches.UsingPOJOClass;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class POJOApprach {
	String studentId2;
	@Test
	void createStudentsUsingPOJOClass() {
		//create object for STudentpojo class 
		StudentsPOJODemo requestBody2= new StudentsPOJODemo();
		requestBody2.setName("ravi");
		requestBody2.setLocation("Chennai");
		requestBody2.setPhone("123456");
		
		String courses[]= {"AI","LLM"};
		requestBody2.setCourses(courses);
		
		studentId2=given()
				.contentType("application/json")
				.body(requestBody2)
				.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo(requestBody2.getName()))
				.body("location", equalTo(requestBody2.getLocation()))
				.body("phone", equalTo(requestBody2.getPhone()))
				.body("courses[0]",equalTo(requestBody2.getCourses()[0]))
				.body("courses[1]",equalTo(requestBody2.getCourses()[1]))
				.header("Content-Type", equalTo("application/json"))
				.log().all()
				.extract().jsonPath().getString("id");
				
		
		
	}
	@AfterMethod
	void DeleteUserusingJSONLibrary() {
		given()
		.when()
			.delete("http://localhost:3000/students/" +studentId2)
		.then()
			.statusCode(200);
	}
}
