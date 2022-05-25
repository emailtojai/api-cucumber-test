package StepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


public class CreateProducts {

    RequestSpecification request;
    Response response;

    @Given("^Setup base url to validate happy scenarios:$")
    public void setupBaseUrlforProduct(String baseurl) {
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("^I submit payload (.*) to api endpoint (.*) for product$")
    public void iSubmitRequestToCreateProductsWithPayload(String payload, String endpoint) {
        response = request.body(payload).post(endpoint).then().extract().response();
    }

    @Then("^I should get product response status code as (.*)$")
    public void iShouldGetresponseStatusCodeForProduct(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response with new product id$")
    public void iShouldGetReponseWithNewProductId() {
        int id = response.getBody().path("id");
        Assert.assertNotNull(id);
    }

    @And("^I should get category name as (.*) in response$")
    public void iShouldGetresponseWithValueCategoryName(String eCategoryName) {
        String aCategoryName = response.getBody().path("category");
        Assert.assertEquals(eCategoryName, aCategoryName);
    }

    @And("^I should get product name as (.*) in response$")
    public void iShouldGetresponseWithValueProductName(String eProductName) {
        String aProductName = response.getBody().path("pname");
        Assert.assertEquals(eProductName, aProductName);
    }

    @And("^I should get price as (.*) in response$")
    public void iShouldGetresponseWithPriceValue(String ePrice) {
        String aPrice = response.getBody().path("price");
        Assert.assertEquals(ePrice, aPrice);
    }

    @And("^I should get company name as (.*) in response$")
    public void iShouldGetresponseWithCompanyName(String eCompanyName) {
        String aCompanyName = response.getBody().path("company");
        Assert.assertEquals(eCompanyName, aCompanyName);
    }

    @And("^I should get price in as (.*) in response$")
    public void iShouldGetresponseWithPriceIn(String ePriceIn) {
        String aPriceIn = response.getBody().path("pricein");
        Assert.assertEquals(ePriceIn, aPriceIn);
    }

    //validate create product scenarios for unhappy path

    @Given("^Setup base url to validate unhappy path scenarios:$")
    public void setupbaseUrlForUhappyPath(String baseurl) {
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }
    @When("^I submit unhappy scenario payload (.*) to api endpoint (.*)$")
    public void iSubmitRequestToCreateProductWithPayloadForUnhappyPath(String payload, String endpoint) {
        response = request.body(payload).post(endpoint).then().extract().response();
    }

    @Then("^I should get product response status code for error as (.*)$")
    public void iShouldGetresponseStatusCodeForForUhappyPath(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get product response with expected error as (.*)$")
    public void iShouldGetErrorMessageResponseForUhappyPath(String expectedError) {
        String actualError = response.getBody().path("error");
        Assert.assertEquals(expectedError, actualError);
    }



}
