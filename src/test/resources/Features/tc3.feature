Feature: Profile picture updation
@PROFILEPIC
  Scenario: validating profile pic updation
    Given user add headers
    When user select form data
    Then user send "POST" request for updateProfilePic
    And User should verify response code with 200
    And User verifies response Profilepicbody message with "Profile updated successfully" 
