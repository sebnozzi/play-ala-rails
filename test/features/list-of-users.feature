Feature: List of users

  In order to see who uses the system, I want to see a list of users

  Scenario: See the list of users
    Given that user "manager" exists
    And that user "secretary" exists
    When I go to the "user list" page
    Then I should see "manager"
    And I should see "secretary"