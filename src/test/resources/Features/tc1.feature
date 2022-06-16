
Feature: Validating Login functionality
@LOGIN
  Scenario: User logins with valid username and password
    Given User add Headers and basicAuth for login
    When User send "POST" request for Login Endpoint
    And User should verify Status code with 200
    And User should verify response body message with "Login successfully" and save logtoken
