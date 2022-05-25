Feature: Title of your feature
  Test customer feature scenarios

  As a user , I want validate customer api scenarios ex: create, update, +
  search and delete with different set of scenarios

  Background:
    Given Setup base url to validate unhappy path scenarios:
      |http://localhost:8082/api/v1|

  Scenario Outline: Create product record and validate the data for scenario <scenario>
    When I submit unhappy scenario payload <payload> to api endpoint <endpoint>
    Then I should get product response status code for error as <status>
    And  I should get product response with expected error as <expectederror>

    Examples:
      | scenario                                      |  payload                                                                                                                         | endpoint       | status  | expectederror                                        |
      | null category name                            |  {"category": "","pname": "iphon 12","company": "apple","price": "1000","pricein": "dollar"}                                     | /products      | 400     |  category must not be empty or undefined             |
      | no special chars for category name            |  {"category": "elctronic@","pname": "iphon 12","company": "apple","price": "1000","pricein": "dollar"}                           | /products      | 400     |  No special chars allowed in category                |
      | not accepted more than 15 chars for category  |  {"category": "elctronic1234567","pname": "iphon 12","company": "apple","price": "1000","pricein": "dollar"}                     | /products      | 400     |  Not allowed more than 15 chars in category          |
      | null product name                             |  {"category": "elctronic","pname": "","company": "apple","price": "1000","pricein": "dollar"}                                    | /products      | 400     |  product name must not be empty or undefined         |
      | no special chars for product name             |  {"category": "elctronic","pname": "iphon 12@","company": "apple","price": "1000","pricein": "dollar"}                           | /products      | 400     |  No special chars allowed in product name            |
      | not accepted more than 15 chars for product   |  {"category": "elctronic","pname": "iphon 12fasdfasfasdfdasdsda","company": "apple","price": "1000","pricein": "dollar"}         | /products      | 400     |  Not allowed more than 15 chars in product name      |
      | null company name                             |  {"category": "elctronic","pname": "iphon 12","company": "","price": "1000","pricein": "dollar"}                                 | /products      | 400     |  company name must not be empty or undefined         |
      | no special chars for company name             |  {"category": "elctronic","pname": "iphon 12","company": "apple@","price": "1000","pricein": "dollar"}                           | /products      | 400     |  No special chars allowed in company name            |
      | not accepted more than 15 chars for company   |  {"category": "elctronic","pname": "iphon 12","company": "appleappleapple12","price": "1000","pricein": "dollar"}                | /products      | 400     |  Not allowed more than 15 chars in company name      |
      | null price                                    |  {"category": "elctronic","pname": "iphon 12","company": "apple","price": "","pricein": "dollar"}                                | /products      | 400     |  price must not be empty or undefined                |
      | no chars in price                             |  {"category": "elctronic","pname": "iphon 12","company": "apple","price": "1000@a","pricein": "dollar"}                          | /products      | 400     |  only digits allowed in price                           |
      | not accepted more than 10 digits for price    |  {"category": "elctronic","pname": "iphon 12","company": "apple","price": "10001000123","pricein": "dollar"}                     | /products      | 400     |  Not allowed more than 10 digits price            |
