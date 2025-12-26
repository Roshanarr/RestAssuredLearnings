package parameters.pathParameters;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathParametersDemo {
	@Test
	void pathParams() {
		given()
			.pathParam("country","India")
		.when()
			.get("https://restcountries.com/v2/name/{country}")
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
