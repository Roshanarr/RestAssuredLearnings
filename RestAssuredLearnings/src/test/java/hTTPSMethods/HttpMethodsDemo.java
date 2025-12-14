package hTTPSMethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HttpMethodsDemo {
	@Test(priority=1)
	void getUser() {
		given()
		.when().get("https://api.restful-api.dev/objects/7")
		.then().statusCode(200);

		
	}
}
