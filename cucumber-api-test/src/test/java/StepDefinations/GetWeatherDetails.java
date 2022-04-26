package StepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;


public class GetWeatherDetails {
	
	RequestSpecification request;
	Response response;
	static final String baseurl = "https://www.metaweather.com/api";
	
	@Given("I setup base url")
	public void i_setup_base_url() {
		RestAssured.baseURI = baseurl;
		request = RestAssured.given();
	}

	@When("^I submit the request to check weather details for city (.*)$")
	public void i_submit_the_request_to_check_weather_details_for_city(String city) {
		String query = "/location/search/?query=" + city;	
		response = request.get(query).then().extract().response();
	}

	@Then("I should get response status code {int}")
	public void i_should_get_response_status_code(int status) {
		Assert.assertEquals(status, response.getStatusCode());
	}
}
