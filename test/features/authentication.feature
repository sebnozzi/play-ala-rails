Feature: Authentication

  Optionally, users can log-in/out of the system
  Logged-in users can post status-changes
  
  Background:
    Given that user "Homer" exists

  Scenario: User logs-in to the system
    Given no user is logged-in
    When I'm in the log-in page
    And I type "Homer" in the "username" field
    And press "Log in"
    Then the logged-in user should be "Homer"

  Scenario: User can log-out of the system
    Given I'm logged-in as "Homer"
    
    When I go to the "user list" page
    Then I should see "log out"
    
    When I go to the posts page of user "Homer"
    Then I should see "log out"
    
    When I click on "log out"
    Then no user should be logged-in

   Scenario: Trying to log-in with missing user
    When I'm in the log-in page
    And press "Log in"
    Then I should see "Missing user"
    
   Scenario: Trying to log-in with invalid user
    When I'm in the log-in page
    And I type "BlahBlah" in the "username" field
    And press "Log in"
    Then I should see "Invalid user"
 
