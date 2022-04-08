@regression @part4
Feature: Introduction to cucumber part 4
  As a test engineer
  I want to be able to write and execute a scenario with steps that have 2 column tables

  Background:
    Given I am on age page

  Scenario: a new scenario with 2-column table
    When I enter values:
      | name | Ann |
      | age  | 5   |
    And I click submit age
    Then I see message: "Hello, Ann, you are a kid"

  Scenario: another new scenario with 2-column table
    When I enter values:
      | name | Bob |
      | age  | 61  |
    And I click submit age
    Then I see message: "Hello, Bob, you are an adult"

  Scenario Outline: a new scenario outline 2
    When I enter values:
      | name | <name> |
      | age  | <age>  |
    And I click submit age
    Then I see message: "<message>"
    Examples:
      | name | age | message                      |
      | Ann  | 5   | Hello, Ann, you are a kid    |
      | Bob  | 61  | Hello, Bob, you are an adult |


# TODO - create Scenario for 'Give us your feedback!' page
  # URL: https://kristinek.github.io/site/tasks/provide_feedback
  # Navigate to page
  # Set Name, Age and Genre
  # - All input MUST be done in single step
  # - All input MUST use Examples for data
  # - Step can use Map or Domain object
  # Click "Send" button and verify that previous input is displayed in correct fields

  Scenario: 'Give us your feedback!' page scenario
    Given I am on feedback page
    When I set values:
      | name   | Tom    |
      | age    | 35     |
      | genre  | male   |
    And I click Send
    Then I see name "Tom" in correct fields
    Then I see age "35" in correct fields
    Then I see genre "male" in correct fields


  # TODO - create Scenario Outline for 'Give us your feedback!' page
  # URL: https://kristinek.github.io/site/tasks/provide_feedback
  # Navigate to page
  # Set Name, Age and Genre
  # - All input MUST be done in single step
  # - All input MUST use Examples for data
  # - Step can use Map or Domain object
  # Click "Send" button and verify that previous input is displayed in correct fields

  Scenario Outline: 'Give us your feedback!' page outline
    Given I am on feedback page
    When I set values:
      | name   | <name>    |
      | age    | <age>     |
      | genre  | <genre>   |
    And I click Send
    Then I see name "<name>" in correct fields
    Then I see age "<age>" in correct fields
    Then I see genre "<genre>" in correct fields
    Examples:
      | name  | age | genre   |
      | Sid   | 21  | male    |
      | Nancy | 20  | female  |



# not from task, just to show sample table with header line

  Scenario: 'Give us your feedback!' page scenario with header table
    Given I am on feedback page
    When I set values with headers:
      | name  | age    | genre   |
      | Tom   | 35     | male   |

    And I click Send
    Then I see name "Tom" in correct fields
    Then I see age "35" in correct fields
    Then I see genre "male" in correct fields