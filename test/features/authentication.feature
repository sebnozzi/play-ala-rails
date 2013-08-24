Feature: Authentication

  Optionally, users can log-in/out of the system
  Logged-in users can post status-changes

  @inProgress
  Scenario: User logs-in to the system
    Given that user "Homer" exists
    And no user is logged-in
    
    When I'm in the log-in page
    And I type "Homer" in the "username" field
    And press "Log in"
    Then the logged-in user should be "Homer"
