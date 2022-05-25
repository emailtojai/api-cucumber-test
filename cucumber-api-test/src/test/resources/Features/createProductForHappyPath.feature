Feature: Test products functionality for happy scenarios
  As a user , I want validate products api scenarios ex: create, update, +
  search and delete with different set of scenarios

  Background:
    Given Setup base url to validate happy scenarios:
      |http://localhost:8082/api/v1|

  Scenario Outline: Create product record and validate the data for scenario <scenario>
    When I submit payload <payload> to api endpoint <eEndpoint> for product
    Then I should get product response status code as <eStatus>
    And  I should get response with new product id
    And  I should get category name as <eCategory> in response
    And  I should get product name as <ePname> in response
    And  I should get price as <ePrice> in response
    And  I should get company name as <eCompany> in response
    And  I should get price in as <ePriceIn> in response

    Examples:
      | scenario                                              |  payload                                                                                                   | eEndpoint       | eStatus     | eCategory          |ePname           | ePrice    | eCompany  | ePriceIn |
      | product name with alpha only                          |  {"category": "electronic", "pname": "iphonex","company": "amazon","price": "1000","pricein": "dollar"}    | /products       | 201         | electronic         |iphonex          | 1000      | amazon    | dollar   |
      | product name with alpha numeric only                  |  {"category": "electronic", "pname": "10210","company": "amazon","price": "1000","pricein": "dollar"}      | /products       | 201         | electronic         |10210            | 1000      | amazon    | dollar   |
      | product name with alpha number                        |  {"category": "electronic", "pname": "iphone 12","company": "amazon","price": "1000","pricein": "dollar"}  | /products       | 201         | electronic         |iphone 12        | 1000      | amazon    | dollar   |
      | category name with alpha only                         |  {"category": "book", "pname": "night by","company": "amazon","price": "1000","pricein": "dollar"}         | /products       | 201         | book               |night by         | 1000      | amazon    | dollar   |
      | category name with alpha numeric only                 |  {"category": "100", "pname": "x10x","company": "amazon","price": "1000","pricein": "dollar"}              | /products       | 201         | 100                |x10x             | 1000      | amazon    | dollar   |
      | category name with alpha number                       |  {"category": "x10x", "pname": "iphone 12","company": "amazon","price": "1000","pricein": "dollar"}        | /products       | 201         | x10x               |iphone 12        | 1000      | amazon    | dollar   |
      | company name with alpha only                          |  {"category": "book", "pname": "night by","company": "amazon","price": "1000","pricein": "dollar"}         | /products       | 201         | book               |night by         | 1000      | amazon    | dollar   |
      | company name with alpha numeric only                  |  {"category": "100", "pname": "x10x","company": "1002","price": "1000","pricein": "dollar"}                | /products       | 201         | 100                |x10x             | 1000      | 1002      | dollar   |
      | company name with alpha number                        |  {"category": "x10x", "pname": "iphone 12","company": "10W10","price": "1000","pricein": "dollar"}         | /products       | 201         | x10x               |iphone 12        | 1000      | 10W10     | dollar   |
      | price min value                                       |  {"category": "book", "pname": "night by","company": "amazon","price": "0","pricein": "dollar"}            | /products       | 201         | book               |night by         | 0         | amazon    | dollar   |
      | price max value                                       |  {"category": "100", "pname": "x10x","company": "1002","price": "5000","pricein": "dollar"}                | /products       | 201         | 100                |x10x             | 5000      | 1002      | dollar   |
      | price negative value                                  |  {"category": "x10x", "pname": "iphone 12","company": "10W10","price": "-1","pricein": "dollar"}           | /products       | 201         | x10x               |iphone 12        | -1        | 10W10     | dollar   |
      | price in fraction                                     |  {"category": "x10x", "pname": "iphone 12","company": "10W10","price": "100.40","pricein": "dollar"}       | /products       | 201         | x10x               |iphone 12        | 100.40    | 10W10     | dollar   |