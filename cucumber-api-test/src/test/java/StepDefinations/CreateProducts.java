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

    @Given("^Setup below product base api url:$")
    public void setupBaseUrlforProduct(String baseurl) {
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("^I submit product (.*) with product payload (.*) to api endpoint (.*)$")
    public void iSubmitRequestToCreateProductsWithPayload(String scenario, String payload, String endpoint) {
        response = request.body(payload).post(endpoint).then().extract().response();
    }

    @Then("^I should get product response status code as (.*) for product$")
    public void iShouldGetresponseStatusCodeForProduct(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response with new product id$")
    public void iShouldGetReponseWithNewProductId() {
        int id = response.getBody().path("id");
        Assert.assertNotNull(id)
        ;
    }

    @And("^I should get response with value product name as (.*) and price as (.*)$")
    public void iShouldGetresponseWithValueProducNameandPrice(String pname, String price) {
        String prodcintName = response.getBody().path("pname");
        String productPrice = response.getBody().path("price");
        Assert.assertEquals(pname, prodcintName);
        Assert.assertEquals(price, productPrice);
    }



}