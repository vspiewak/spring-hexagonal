Feature: Swagger can be retrieved

  Scenario: client make a call api-docs
    When the client make a GET on /v3/api-docs
    Then the client receives a status code of 200
    And the client receives openapi with value '3.1.0'