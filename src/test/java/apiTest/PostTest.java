package apiTest;

import static io.restassured.RestAssured.given;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.PostBody;

public class PostTest {
	@BeforeTest
	public static void setup() {
		RestAssured.baseURI = System.getenv("baseURI");
	}

	@Test
	public void PostAPITest() {
		PostBody PostBodyObj = new PostBody();
		PostBodyObj.setName("Laxmikanth");
		PostBodyObj.setDescription("POstAPI");
		PostBodyObj.setAccount("Acc1");
		Gson gson = new Gson();
		String requestBody = gson.toJson(PostBodyObj);
		Response response = given().header("Content-type", "application/json").and().body(requestBody).when()
				.post("/API/V2/tag").then().extract().response();

		AssertJUnit.assertEquals(201, response.statusCode());
		AssertJUnit.assertEquals("Laxmikanth", response.jsonPath().getString("name"));
		AssertJUnit.assertEquals("PostAPI", response.jsonPath().getString("Description"));
		AssertJUnit.assertEquals("ACC1", response.jsonPath().getString("Account"));
	}
}
