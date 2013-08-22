Feature: Posts of User(s)

  The goal of the system is to see what the users have been up to.
  
  Background:
    Given that user "manager" exists
    And that user "manager" posted
      | first day at work   |
      | meeting people      | 
      | working like crazy  |

  Scenario: See the posts of a user
    When I go to the posts page of user "manager"
    Then I should see 3 posts
    And I should see "Posts of manager"
    
  Scenario: Posts are ordered chronologically (newest on top)
    When I go to the posts page of user "manager"
    Then the post nr. 1 should start with "working"
    And the post nr. 2 should start with "meeting"
    And the post nr. 3 should start with "first"

  