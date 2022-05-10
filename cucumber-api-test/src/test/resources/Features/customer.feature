Feature: Test Customer API Feature Scenarios

  As a user , I want validate customer api scenarios ex: create, update, +
  search and delete with different set of scenarios.

  Background:
    Given Setup Base API URL:
      |http://localhost:8081/api/v1|

  Scenario Outline: <scenario>
    When I submit Scenario <scenario> with payload <payload> to API endpoint <endpoint>
    Then I should get Response Status Code as <status>
    And  I should get Response with new Customer ID
    And  I should get Response with value First Name as <expectedfirstname> and Last Name as <expectedlastname>

    Examples:
      | scenario                                    |  payload                                                                                                                              | endpoint        | status      | expectedfirstname           | expectedlastname   |
      | Create new customer with alpha numeric      |  {"fname": "John123", "lname": "Adam123","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]} | /customers      | 500         | John123          | Adam123     |
      | Create new customer with numeric            |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 310         | 2345123         | 542342  |
      | Create new customer with nullfirstname      |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |
      | Create new customer with nulllastname       |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |
      | Create new customer with nullfirstlastname  |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |
      | Create new customer with nulladdress        |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |
      | Create new customer with specialcharacters  |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |
      | Create new customer with extralongsting     |  {"fname": "2345123", "lname": "542342","addresses": [{"address": "321, Sliver rd","city": "Ashland","state": "NY","zip": "98654"}]}  | /customers      | 201         | 2345123         | 542342  |