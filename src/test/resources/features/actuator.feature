Feature: Actuator Health can be retrieved

  Scenario: client make a call to actuator health
    When the client make a GET on /actuator/health
    Then the client receives a status code of 200
    And the client receives status with value 'UP'