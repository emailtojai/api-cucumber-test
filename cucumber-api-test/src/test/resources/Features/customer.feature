Feature: Title of your feature
  Test customer feature scenarios

  As a user , I want validate customer api scenarios ex: create, update, +
  search and delete with different set of scenarios

  Background:
    Given Setup below base api url :
      |http://localhost:8081/api/v1|

  Scenario Outline: Create customer record and validate the data
    Given I want to validate <scenarios>
    When I submit submit payload <payload> to api endpoint <endpoint>
    Then I should get response status code as <status>
    And  I should get response with new customer id
    And  I should get response with value first name as <fname> and last name as <lname>

    Examples:
      | scenarios                                 |  payload                                                                                                                              | endpoint        | status      | fname           | lname   |
      | create new customer with alpha numeric    |  {"fname": "shy123", "lname": "sky","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}      | /customers      | 201         | shy123          | sky     |
      | create new customer with numeric          |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |
