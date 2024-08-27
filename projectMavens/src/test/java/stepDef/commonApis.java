package stepDef;



import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.internal.org.jline.utils.Log;
import payload.postRequest;
import urls.apiUrl;

public class commonApis {

	RequestSpecification requestAPI;
	Response responseAPI;
	String ids;


	private static final Logger logger = LogManager.getLogger(commonApis.class);

	@Given("pass the prerequest data in post api along with request")
	public void pass_the_prerequest_data_in_post_api_along_with_request() {
		RestAssured.baseURI=apiUrl.baseUrl.endpoint();
		requestAPI = RestAssured.given().log().all().
				header("Content-Type",ContentType.JSON).body(postRequest.postReq());

	}
	@When("execute the POST API")
	public void execute_the_post_api() {
		responseAPI = requestAPI.when().post(apiUrl.createpostapi.endpoint());

	}
	@Then("should return {int} response code")
	public void should_return_response_code(Integer statuscode) {

		responseAPI.then().log().all().statusCode(statuscode);
	}
	@And("verify the {string} id is matching with exepected result")
	public void verify_the_id_is_matching_with_exepected_result(String jobs) {
		responseAPI.then().body("job", equalTo("leader"));
	}

	@And("get the Job id from post api response")
	public void get_the_job_id_from_post_api_response() {
		ids=  responseAPI.jsonPath().getString("id");
		logger.info("print id value:" +ids);
	}

	//	GET API

	@When("execute the GET API")
	public void execute_the_get_api() {
		responseAPI = requestAPI.when().get(apiUrl.validateunknownapi.endpoint());
	}

	@Then("get the year {int}  name has to  matching with \"aqua sky\"exepected result")
	public void get_the_year_name_has_to_matching_with_aqua_sky_exepected_result(Integer int1) {


		List<Map<String, Object>> dataList = responseAPI.jsonPath().getList("data");

        // Filter the data for the year
        Map<String, Object> yearData = dataList.stream()
                .filter(data -> int1.equals(data.get("year")))
                .findFirst()
                .orElse(null);

        // Validate the color status
        if (yearData != null) {
            String color = (String) yearData.get("color");
            System.out.println("Color for the year " + int1 + ": " + color);
            Assert.assertEquals("#98B2D1", color);
        } else {
            Assert.fail("Data for year " + int1 + " not found.");
        }
    }
}