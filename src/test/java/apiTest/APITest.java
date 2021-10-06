package apiTest;

import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APITest {
	

	@BeforeTest
	public static void setup() {
		RestAssured.baseURI = System.getenv("baseURI");
	}

	@Test
	public void getAPITest() {
		Response response = given().contentType(ContentType.JSON).when().get("/API/V2/tag/byname?tagname=Laxmikanth").then()
				.extract().response();

		AssertJUnit.assertEquals(200, response.getStatusCode());
		AssertJUnit.assertEquals("Laxmikanth", response.jsonPath().getString("name"));
		AssertJUnit.assertEquals("PostAPI", response.jsonPath().getString("Description"));
		AssertJUnit.assertEquals("ACC1", response.jsonPath().getString("Account"));

	}

	
}
