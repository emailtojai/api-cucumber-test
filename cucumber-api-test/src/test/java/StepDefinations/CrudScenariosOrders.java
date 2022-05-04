package StepDefinations;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.File;


public class CrudScenariosOrders {


    RequestSpecification request;
    Response response;
    static String gEndPoint;
    static int gOrderId;

    // Scenario to create new orders record
    @Given("^Setup below order base api url:$")
    public void setUpBaseUrlForOrders(String baseurl) {
        RestAssured.baseURI = baseurl;
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("^I submit order payload to api end point:$")
    public void iSubmitRequestToCreateOrdersWithPayload(DataTable orderTable) throws Exception {
        List<List<String>> rows = orderTable.asLists(String.class);
        String payLoad = rows.get(0).get(0);
        String endPoint = rows.get(0).get(1);
        String json = new String(Files.readAllBytes(Paths.get(payLoad)));
        response = request.body(json).post(endPoint).then().extract().response();
    }

    @Then("^I should get response status code for orders as:$")
    public void iShouldGetresponseStatusCodeForOrders(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        int status = Integer.parseInt(rows.get(0).get(0));
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response with new order id and capture in global variable for another scenarios$")
    public void iShouldCaptureOrderIdToGlobalVar() {
        gOrderId = response.getBody().path("id");
        //System.out.println(gOrderId);
    }

    @And("^I should get response with trackingno:$")
    public void iShouldGetResponseWithTrackingno(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        String eTrackingno = rows.get(0).get(0);
        String trackingno = response.getBody().path("trackingno");
        Assert.assertEquals(eTrackingno, trackingno);
    }

    // Scenario to  update order for new created order record

    @When("^I append above created order id to orders endpoint to update order:$")
    public void iAppendOrderIdtoEndPoint(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        String endpoint = rows.get(0).get(0);
        gEndPoint = endpoint + "/" + gOrderId;
    }

    @And("^I submit put request with orders payload to end point:$")
    public void iSubmitPutRequestWithPayLoadtoEndpoint(DataTable orderTable) throws Exception {
        List<List<String>> rows = orderTable.asLists(String.class);
        String payload = rows.get(0).get(0);
        String json = new String(Files.readAllBytes(Paths.get(payload)));
        response = request.body(json).put(gEndPoint).then().extract().response();
    }

    @Then("^I should get response status code for updated orders as:$")
    public void iShouldGetResponseForOrders(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        int status = Integer.parseInt(rows.get(0).get(0));
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response with same order id as created above$")
    public void iShouldGetResponseWithSameOrderId() {
        int orderId = response.getBody().path("id");
        Assert.assertEquals(gOrderId, orderId);
    }

    @And("^I should get response with trackingno and price:$")
    public void iShouldGetResponseWithTrackingNoAndPrice(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        String eTrackingNo = rows.get(0).get(0);
        String ePrice = rows.get(0).get(1);

        String  trackingno = response.getBody().path("trackingno");
        String price = response.getBody().path("price");
        Assert.assertEquals(eTrackingNo, trackingno);
        Assert.assertEquals(ePrice, price);
    }

    // Scenario to delete created order

    @When("^I append above created order id to orders endpoint:$")
    public void iAppendAboveCreatedOrderToDeleteOrders(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        String endpoint = rows.get(0).get(0);
        gEndPoint = endpoint + "/" + gOrderId;
    }

    @And("^I submit delete request to orders end point$")
    public void iSubmitDeleteRequestForOrders() {
        response = request.delete(gEndPoint).then().extract().response();
    }

    @Then("^I should get response status code for deleted order as:$")
    public void iShouldGetResponseCodeForDeletedRecord(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        int status = Integer.parseInt(rows.get(0).get(0));
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^I should get response body as:$")
    public void iShouldGetResponseBodyAsNull(DataTable orderTable) {
        List<List<String>> rows = orderTable.asLists(String.class);
        String eResBody = rows.get(0).get(0);

        String aBody = response.getBody().asString();
        Assert.assertEquals(eResBody, aBody);
    }

}
