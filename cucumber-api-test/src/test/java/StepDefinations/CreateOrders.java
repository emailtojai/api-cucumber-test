//package StepDefinations;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.junit.Assert;
//import java.io.File;
//
//
//public class CreateOrders {
//
//
//    RequestSpecification request;
//    Response response;
//    String gEndPoint;
//    int gOrderId;
//
//    // Scenario to create new orders record
//    @Given("^Setup below order base api url:$")
//    public void setUpBaseUrlForOrders(String baseurl) {
//        RestAssured.baseURI = baseurl;
//        request = RestAssured.given().header("Content-Type", "application/json");
//    }
//
//    @When("^I submit order payload (.) to api end point (.)$")
//    public void iSubmitRequestToCreateOrdersWithPayload(String scenario, String payload, String endpoint) {
//        File jsonDataInFile = new File(payload);
//        response = request.body(jsonDataInFile).post(endpoint).then().extract().response();
//    }
//
//    @Then("^I should get response status code for orders as (.*)$")
//    public void iShouldGetresponseStatusCodeForOrders(int status) {
//        Assert.assertEquals(status, response.getStatusCode());
//    }
//
//    @And("^I should get response with new order id and capture in global variable for another scenarios$")
//    public void iShouldCaptureOrderIdToGlobalVar(String trackingno) {
//        gOrderId = response.getBody().path("id");
//    }
//
//    @And("^I should get response with trackingno:$")
//    public void iShouldGetResponseWithTrackingno(String trackingno) {
//        String eTrackingno = response.getBody().path("trackingno");
//        Assert.assertEquals(trackingno, eTrackingno);
//    }
//
//    // Scenario to  update order for new created order record
//
//    @When("^I append above created order id to orders endpoint (.*) to update order$")
//    public void iAppendOrderIdtoEndPoint(String endpoint) {
//        gEndPoint = endpoint + "/" + gOrderId;
//    }
//
//    @And("^I submit put request with orders payload (.) to end point (.)$")
//    public void iSubmitPutRequestWithPayLoadtoEndpoint(String payload, String endpoint) {
//        File jsonDataInFile = new File(payload);
//        response = request.body(jsonDataInFile).put(endpoint).then().extract().response();
//    }
//
//    @Then("^I should get response code (.*) for orders$")
//    public void iShouldGetResponseForOrders(int status) {
//        Assert.assertEquals(status, response.getStatusCode());
//    }
//
//    @And("^I should get response with same order id as created above$")
//    public void iShouldGetResponseWithSameOrderId() {
//        int orderid = response.getBody().path("id");
//        Assert.assertEquals(gOrderId, orderid);
//    }
//
//    @And("^I should get response with trackingno and price:$")
//    public void iShouldGetResponseWithTrackingNoAndPrice(String eTrackingNo, String  ePrice) {
//        String  trackingno = response.getBody().path("trackingno");
//        int price = response.getBody().path("price");
//        Assert.assertEquals(eTrackingNo, trackingno);
//        Assert.assertEquals(ePrice, price);
//    }
//
//
//    // Scenario to delete created order
//
//    @When("^I append above created order id to orders endpoint (.*) to delete order$")
//    public void iAppendAboveCreatedOrderToDeleteOrders(String endpoint) {
//        gEndPoint = endpoint + "/" + gOrderId;
//    }
//
//    @And("^I submit delete request to orders end point$")
//    public void iSubmitDeleteRequestForOrders() {
//        response = request.delete(gEndPoint).then().extract().response();
//    }
//
//    @Then("^I should get response code (.*) for deleted order$")
//    public void iShouldGetResponseCodeForDeletedRecord(int status) {
//        Assert.assertEquals(status, response.getStatusCode());
//    }
//
//    @And("^I should not get response body as null$")
//    public void iShouldGetResponseBodyAsNull() {
//        String sBody = response.getBody().asString();
//        Assert.assertNull(sBody);
//    }
//
//
//
//}