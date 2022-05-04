Feature: Title of your feature
  Test orders functionalities

  As a user , I want to validate orders api scenarios ex: create, update ,
  search and delete with different set of scenarios

  Background:
    Given Setup below order base api url:
      |http://localhost:8083/api/v1|

  Scenario: Create order record and validate the data
    When I submit order payload to api end point:
      | src/test/resources/payloads/orders/create-order.json | /orders |
    Then I should get response status code for orders as:
      | 201  |
    And  I should get response with new order id and capture in global variable for another scenarios
    And  I should get response with trackingno:
      |TP087463563|

  Scenario: Update above created order details using order id and validate record is updated as expected
    When I append above created order id to orders endpoint to update order:
      |  /orders   |
    And I submit put request with orders payload to end point:
      | src/test/resources/payloads/orders/update-order.json  |
    Then I should get response status code for updated orders as:
      | 200  |
    And  I should get response with same order id as created above
    And  I should get response with trackingno and price:
      |TP087463563|700|

  Scenario: Delete above create and updated order using order id and validate record is deleted from database
    When I append above created order id to orders endpoint:
      |  /orders   |
    And I submit delete request to orders end point
    Then I should get response status code for deleted order as:
      | 200  |
    And I should get response body as:
      | {}  |


