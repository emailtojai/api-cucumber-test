Feature: Test products features

  As a user , I want validate products api scenarios ex: create, update, +
  search and delete with different set of scenarios

  Background:
    Given Setup below product base api url:
      |http://localhost:8082/api/v1|

  Scenario Outline: <scenario>
    When I submit product <scenario> with product payload <payload> to api endpoint <endpoint>
    Then I should get product response status code as <status> for product
    And  I should get response with new product id
    And  I should get response with value product name as <epname> and price as <eprice>

    Examples:
      | scenario                                              |  payload                                                                                                  | endpoint       | status      | epname           | eprice    |
      | Create product record with alpha numeric name         |  {"category": "elctronic", "pname": "iphon 12","company": "apple","price": "1000","price in": "dollar"}   | /products      | 201         | iphon 12         | 1000      |
      | Create product record with numeric name               |  {"category": "elctronic", "pname": "iphonX","company": "apple","price": "500","price in": "dollar"}      | /products      | 201         | iphonX           | 500       |
      | Create product record with special char name          |  {"category": "elctronic", "pname": "$%^$#@#$","company": "XSD","price": "200","price in": "dollar"}      | /products      | 201         | $%^$#@#$         | 200       |