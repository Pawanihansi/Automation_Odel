Feature: Login to the system
  As a user
  I should be able to login tp the system

  Background:
    When I have opened the system
    And I click on "Login_Icon"
    And I wait few seconds
    And I click on "Login"

  @LoginWithoutCredentials
  Scenario Outline:  Verify the login without credentials
    When I click on "Login_LoginButton"
    Then I see the exact "<HTMLformValidationMessage>" display on validation message(HTML form validation) "Login_UsernameField"
    Examples:
      | HTMLformValidationMessage |
      | HTMLformValidationMessage |

  @LoginInvalidCredentials @InvalidPassword
  Scenario Outline:  Verify the login with invalid password
    When I type "<validUsername>" to the "Login_UsernameField"
    And I wait few seconds
    And I type "<password>" to the "Login_Password"
    And I click on "Login_LoginButton"
    Then I see the exact "<message>" display on "Login_UsernameFieldText"
    Examples:
      | validUsername | password | message             |
      | validUsername | password | invalidLoginMessage |
      | validUsername | password2 | invalidLoginMessage |


  @LoginValidCredential
  Scenario Outline: Verify the login with valid credentials
    When I type "<validUsername>" to the "Login_UsernameField"
    And I type "<validPassword>" to the "Login_Password"
    And I click on "Login_LoginButton"
    Examples:
      | validUsername | validPassword |
      | validUsername | validPassword |

