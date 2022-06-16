Feature: Validating Address related functionality

  @ADDADDRESS
  Scenario Outline: 
    : User adding address in his account

    Given User add Headers and basic auth
    Then User send request body with his Firstname "<firstname>",lastname "<Lastname>" ,phone number "<phone number>",Apartment name "<apartment name>", State "<state>",city "<city>",Country "<country>",Zipcode"<zipcode>",address"<address>",AddressType "<addresstype>"
    When User send "POST" request for Addaddress Endpoint
    And User should verify response code with 200
    And User should verify addaddress response body message  with  "Address added successfully"

    Examples: 
      | firstname | Lastname | phone number | apartment name | state | city | country | zipcode | address | addresstype |
      | Sarath    | kumar    |   9003312345 | casa           |    33 | 3378 |     101 |  202020 | chennai | Home        |

  @UPDATEADDRESS
  Scenario Outline: User Updating address in his account
    Given User add Headers and basic auth
    Then User send request body with his id  <Address id>  Firstname "<firstname>",lastname "<Lastname>" ,phone number "<phone number>",Apartment name "<apartment name>", State "<state>",city "<city>",Country "<country>",Zipcode"<zipcode>",address"<address>",AddressType "<addresstype>"
    When User send "PUT" request for UpdateAddress Endpoint
    And User should verify response code with 200
    And User should verify updateAddress response body message with "Address updated successfully"

    Examples: 
      | Address id | firstname | Lastname | phone number | apartment name | state | city | country | zipcode | address | addresstype |
      |       1234 | Sarath    | sarath   |   9003312345 | casa           |    33 | 3378 |     101 |  202020 | chennai | Home        |

  @GETADDRESS
  Scenario: User Get all address in his account
    Given User add Headers and basic auth
    When User send "GET" request for GETAddress Endpoint
    And User should verify response body message and status code with "OK" and 200

  @DELETEADDRESS
  Scenario: User Delete address in his account
    Given User add Headers and basic auth
    When User send "DELETE " request body with Address Id
    And User should verify Delete address response body message with "Address deleted successfully"
