package StepDefinations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import java.io.File;


public class CreateCustomer {


    RequestSpecification request;
    Response response;

    @Given("^Setup Base API URL:$")
    public void setupbaseurl(String baseurl) {
        System.out.println(baseurl);
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("^I submit Scenario (.*) with payload (.*) to API endpoint (.*)$")
    public void iSubmitRequestToCreateCustomerWithPayload(String scenario, String payload, String endpoint) {
       response = request.body(payload).post(endpoint).then().extract().response();
    }

    @Then("^I should get Response Status Code as (.*)$")
    public void iShouldGetresponseStatusCode(int status) {
        Assert.assertEquals(status, response.getStatusCode());

    }

    @And("^I should get Response with new Customer ID$")
    public void iShouldGetReponseWithNewCustomerId() {
        int id = response.getBody().path("id");
        Assert.assertNotNull(id);
    }

    @And("^I should get Response with value First Name as (.*) and Last Name as (.*)$")
    public void iShouldGetresponseWithValueFirstNameandLastName(String fname, String lname) {
        String firstname = response.getBody().path("fname");
        String lastname = response.getBody().path("lname");
        Assert.assertEquals(fname, firstname);
        Assert.assertEquals(lname, lastname);
    }
}