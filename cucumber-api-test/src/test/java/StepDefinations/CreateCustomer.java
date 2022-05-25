//json-server --routes ./src/test/resources/mockdata/routes.json -p 8081 ./src/test/resources/mockdata/customers-db.json --middlewares ./src/test/resources/middleware/validator.js
//json-server --routes ./src/test/resources/mockdata/routes.json -p 8082 ./src/test/resources/mockdata/products-db.json --middlewares ./src/test/resources/middleware/validator.js
//json-server --routes ./src/test/resources/mockdata/routes.json -p 8083 ./src/test/resources/mockdata/orders-db.json --middlewares ./src/test/resources/middleware/validator.js
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

    @Given("^Setup below base api url:$")
    public void setupbaseurl(String baseurl) {
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("^I submit scenario (.*) with payload (.*) to api endpoint (.*)$")
    public void iSubmitRequestToCreateCustomerWithPayload(String scenario, String payload, String endpoint) {
        response = request.body(payload).post(endpoint).then().extract().response();
    }

    @Then("^I should get response status code as (.*)$")
    public void iShouldGetresponseStatusCode(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response with new customer id$")
    public void iShouldGetReponseWithNewCustomerId() {
        int id = response.getBody().path("id");
        Assert.assertNotNull(id);
    }

    @And("^I should get response with value first name as (.*) and last name as (.*)$")
    public void iShouldGetresponseWithValueFirstNameandLastName(String fname, String lname) {
        String firstname = response.getBody().path("fname");
        String lastname = response.getBody().path("lname");
        Assert.assertEquals(fname, firstname);
        Assert.assertEquals(lname, lastname);
    }

    //validate create customers scenarios for invalid field's value

    @Given("^Setup base url to validate fields value:$")
    public void setupbaseUrlForInvalid(String baseurl) {
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }
    @When("^I submit and validate scenario (.*) with payload (.*) to api endpoint (.*)$")
    public void iSubmitRequestToCreateCustomerWithPayloadForValidateFields(String scenario, String payload, String endpoint) {
        response = request.body(payload).post(endpoint).then().extract().response();
    }

    @Then("^I should get response status code for error as (.*)$")
    public void iShouldGetresponseStatusCodeForValidateFields(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response with expected error as (.*)$")
    public void iShouldGetReponseForErrorMessage(String expectedError) {
        String actualError = response.getBody().path("error");
        Assert.assertEquals(expectedError, actualError);
    }
}
