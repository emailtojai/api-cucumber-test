#Feature: Title of your feature
#  Test orders functionalities
#
#  As a user , I want validate orders api scenarios ex: create, update, +
#  search and delete with different set of scenarios
#
#  Background:
#    Given Setup below order base api url:
#      |http://localhost:8083/api/v1|
#
#  Scenario : Create order record and validate the data
#    When I submit order payload <payload> to api end point <endpoint>
#      | payload                                                      |  endpoint  |
#      | ./src/test/resources/payloads/orders/create-order.json         |  /orders   |
#    Then I should get response status code for orders as <status>
#      |status|
#      | 201  |
#    And  I should get response with new order id and capture in global variable for another scenarios
#    And  I should get response with trackingno:
#      |TP087463563|
#
#  Scenario : Query created order using order id which is captured during order creation and use to update order record
#    When I append above created order id to orders endpoint <endpoint> to update order
#      |  endpoint  |
#      |  /orders   |
#    And I submit put request with orders payload <payload> to end point <endpoint>
#      | payload                                                      |  endpoint  |
#      | src/test/resources/payloads/orders/update-order.json         |  /orders |
#    Then I should get response code <status> for orders
#      |status|
#      | 200  |
#    And  I should get response with same order id as created above
#    And  I should get response with trackingno and price:
#      |TP087463563|700|
#
#  Scenario : Query created order using order id which is captured during order creation and use to delete order record
#    When I append above created order id to orders endpoint <endpoint> to delete order
#      |  endpoint  |
#      |  /orders   |
#    And I submit delete request to orders end point
#    Then I should get response code <status> for deleted order
#      |status|
#      | 200  |
#    And I should not get response body as null