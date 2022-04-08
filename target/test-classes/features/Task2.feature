Feature: Add, remove, edit a person in People with jobs page


  Scenario Outline: add a new person outline
    Given I am on People with jobs page
    Given There are 3 people on the original list
    Given I click on Add person button
    And I am sent to enter a new person page
    And I enter name of a new person: "<name>"
    And I enter job of a new person: "<job>"
    And I click on Add
    Then I am set back to home page
    And There are 4 people on the list now
    And Person is added to the list with name "<name>" and job title "<job>"

    Examples:
      | name | job     |
      | Mia  | Dentist |

  Scenario: Edit person scenario
    Given I am on People with jobs page
    When I click edit button next to Mike
    Then I am sent to page for editing Mikes profile
    And I can change name and job:
      | name | Zike   |
      | job  | Tester |
    And I can click edit to save changes
    And I am set back to home page
    And the list now has the edited name and job:
      | name | Zike   |
      | job  | Tester |


  Scenario: remove person scenario
    Given I am on People with jobs page
    Given There are 3 people on the original list
    When I click delete button next to Jill
    Then There are 2 people on the list
    And "Jill" is not on the list


  Scenario: clear fields in adding user
    Given I am on a new person page
    And I enter name and job:
      | name | Luke      |
      | job  | Assistant |
    When I click on Clear all fields
    Then Name and job fields are cleared and empty



  Scenario Outline: reset list after add new person
    Given I am on People with jobs page
    Given I click on Add person button
    And I am sent to enter a new person page
    And I enter name of a new person: "<name>"
    And I enter job of a new person: "<job>"
    And I click on Add
    Then I am set back to home page
    And There are 4 people on the list now
    And Person is added to the list with name "<name>" and job title "<job>"
    And I click on Reset list
    Then List is set back to original

    Examples:
      | name | job     |
      | Mia  | Dentist |


  Scenario: reset list after remove person scenario
    Given I am on People with jobs page
    Given There are 3 people on the original list
    When I click delete button next to Jill
    Then There are 2 people on the list
    And "Jill" is not on the list
    And I click on Reset list
    Then List is set back to original


  Scenario: reset list after Edit person scenario
    Given I am on People with jobs page
    When I click edit button next to Mike
    Then I am sent to page for editing Mikes profile
    And I can change name and job:
      | name | Zike   |
      | job  | Tester |
    And I can click edit to save changes
    And I am set back to home page
    And the list now has the edited name and job:
      | name | Zike   |
      | job  | Tester |
    And I click on Reset list
    Then List is set back to original


