Feature: Customers API Unhappy path Scenarios

  As a user, I want validate customer api Unhappy path scenarios

  Background:
    Given Setup base url to validate fields value:
      |http://localhost:8081/api/v1|

  Scenario Outline: Validate create customers scenarios for invalid field's value <scenario>
    When I submit and validate scenario <scenario> with payload <payload> to api endpoint <endpoint>
    Then I should get response status code for error as <status>
    And  I should get response with expected error as <expectederror>

    Examples:
      | scenario                                          |  payload                                                                                                                                          | endpoint        | status  | expectederror                                           |
      | null first name                                   |  {"fname": "", "lname": "Adam123","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}                    | /customers      | 400     |  first name must not be empty or undefined              |
      | null last name                                    |  {"fname": "2345123", "lname": "","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}                    | /customers      | 400     |  last name must not be empty or undefined               |
      | no special chars for first name                   |  {"fname": "Jhon#!", "lname": "summer","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}               | /customers      | 400     |  No special chars allowed in first name                 |
      | no special chars for last name                    |  {"fname": "Jhon", "lname": "summer@","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}                | /customers      | 400     |  No special chars allowed in last name                  |
      | not accepted more than 15 chars for first name    |  {"fname": "Jhonxyzertulo451", "lname": "summer","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}     | /customers      | 400     |  Not allowed more than 15 chars in first name           |
      | not accepted more than 15 chars for last name     |  {"fname": "Jhon", "lname": "summeriolkjh1233","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}       | /customers      | 400     |  Not allowed more than 15 chars in last name            |
      | null address                                      |  {"fname": "Jhon", "lname": "summer","addresses": [{"address": "","city": "Ashland","state": "NY","zip": "98654"}]}                               | /customers      | 400     |  Address must not be empty or undefined                 |
      | null city                                         |  {"fname": "Jhon", "lname": "summer","addresses": [{"address": "321, Sliver rd","city": "","state": "NY","zip": "98654"}]}                        | /customers      | 400     |  city must not be empty or undefined                    |
      | null state                                        |  {"fname": "Jhon", "lname": "summer","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "","zip": "98654"}]}                   | /customers      | 400     |  state must not be empty or undefined                   |
      | null zipcode                                      |  {"fname": "Jhon", "lname": "summer","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": ""}]}                      | /customers      | 400     |  zip code must not be empty or undefined                |
      | zipcode not greater than 5 digits                 |  {"fname": "Jhon", "lname": "summer","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "986542"}]}                | /customers      | 400     |  Not allowed more than 5 digits zip code                 |