Feature: List of users

  In order to see who uses the system, I want to see a list of users

  Background:
    Given that user "Homer" exists
    And that user "Smithers" exists
  
  Scenario: See the list of users
    When I go to the "user list" page
    Then I should see "Homer"
    And I should see "Smithers"
    
  Scenario: Click on a user, land on the posts page
    When I go to the "user list" page
    And I click on "Homer"
    Then I should be on the posts page of "Homer"
 