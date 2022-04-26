Feature: Title of your feature
  Get weather details for different cities
  
  As a user , I want to submit get request 
  for different city and want to check 
  the weather details

  Scenario Outline: Get Weather Details
    Given I setup base url
    When I submit the request to check weather details for city <city>
    Then I should get response status code <status>

    Examples: 
      | status | city           |
      |    200 | london         |
      |    200 | San Francisco 	|
      |    500 | Busan 			|
