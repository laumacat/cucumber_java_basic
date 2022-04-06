Feature: Enter a number to get square root
  As a user
  I want to be able to enter a number and get the square root of it,
  or see an appropriate error message if entered value is not accepted.


  Scenario Outline: error message for invalid input
    Given I am on enter a number page
    When I enter invalid input: "<invalidNumber>"
    And I click submit
    Then I see error message: "<message>"

    Examples:
      | invalidNumber | message               |
      | 16            | Number is too small   |
      | 101           | Number is too big     |
      | aaa           | Please enter a number |


  Scenario Outline: square root of valid input
    Given I am on enter a number page
    When I enter valid input: "<validNumber>"
    And I click submit
    Then I see message with answer: "<message>"
    And I can click OK

    Examples:
      | validNumber | message                    |
      | 64          | Square root of 64 is 8.00  |
      | 85          | Square root of 85 is 9.22  |