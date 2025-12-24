package hTTPSMethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPSMethodsDemo {

	String  userId;
	//1)Test to validate the retreive the users and validate the response
	@Test(priority=1,enabled=true)
	void getUsers() {
		given()
		.when()
			.get("http://localhost:3000/students/1")
		.then()
			.statusCode(200)
			.body("id", equalTo("1"))
			.body(containsString("location"))
			.header("Content-Type", equalTo("application/json"))
			.time(lessThan(800L))
			.log().all();
		
	}
	
	//2)Test to Create User  and validate the response
	@Test(priority=2)
	void CreateUser() {
		//Declaration/creation of HaahMap
		HashMap<String,Object> data= new HashMap<String,Object>();
		//adding data into Hashmap
		data.put("name", "baw");
		data.put("salary", "123");
		data.put("age", "123");
		
		userId=given()
			.contentType("application/json")
			.body(data)
		.when().post("http://localhost:3000/students")
		.then()
			.statusCode(201)
		.header("Content-Type", equalTo("application/json"))
		.body("name", equalTo("baw"))
		.log().all()
		.extract().jsonPath().getString("id");
	}
	
	//3)Test to update the user and validate the response
	@Test(priority=3,dependsOnMethods={"CreateUser"})
	void Updateuser() {
		HashMap<String,Object> data= new HashMap<String,Object>();
		//adding data into Hashmap
		data.put("name", "ram");
		data.put("salary", "234");
		data.put("age", "123");
		
		given()
		.contentType("application/json")
		.body(data)
	.when()
		.put("http://localhost:3000/students/"+ userId )
		.then()
			.statusCode(200)
			.header("Content-Type", equalTo("application/json"))
			.body("name", equalTo("ram"))
		.body("salary", equalTo("234"))
		.body("age", equalTo("123"))
		.log().all();
		
		
	}
	//4)Test to delete the user and validate the response
	@Test(priority=4,dependsOnMethods={"CreateUser","Updateuser"})
	void deleteUser()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/"+ userId )
		.then()
			.statusCode(200)
			.log().all();
	}
}
