Feature: Customers API Happy Path Scenarios

  As a user , I want validate customer api scenarios ex: create, update, +
  search and delete with different set of scenarios

  Background:
    Given Setup below base api url:
      |http://localhost:8081/api/v1|

  Scenario Outline: Create customer record and validate the data
    When I submit scenario <scenario> with payload <payload> to api endpoint <endpoint>
    Then I should get response status code as <status>
    And  I should get response with new customer id
    And  I should get response with value first name as <expectedfirstname> and last name as <expectedlastname>

    Examples:
      | scenario                                    |  payload                                                                                                                              | endpoint        | status      | expectedfirstname           | expectedlastname   |
      | create new customer with alpha numeric      |  {"fname": "John123", "lname": "Adam123","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]} | /customers      | 201         | John123          | Adam123     |
      | create new customer with numeric            |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |