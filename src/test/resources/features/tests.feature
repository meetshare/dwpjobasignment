Feature: DWP Assignment

Scenario: Get all users from the given users endpoint
  When sending the request to "users"
  Then verify the successful response
  And verify the number of "1000" return

Scenario: Get all users from the given city endpoint
  When sending the request to "city/London/users"
  Then verify the successful response
  And verify the number of "6" return

Scenario: Get all users within 50 miles radius of London using the city users endpoint
  When sending the request to "city/London/users"
  Then verify the successful response
  And print users to console
