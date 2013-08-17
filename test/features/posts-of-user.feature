Feature: Posts of User(s)

  The goal of the system is to see what the users have been up to.
  
  Scenario: See the posts of a user
    Given that user "manager" exists
    And that user "manager" posted "first day at work"
    And that user "manager" posted "meeting people"
    And that user "manager" posted "working like crazy"
    When I go to the posts page of user "manager"
    Then I should see 3 posts