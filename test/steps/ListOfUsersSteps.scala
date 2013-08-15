package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import cucumber.runtime.PendingException

class ListOfUsersSteps extends ScalaDsl with EN with ShouldMatchers {

  Given("""^that user "([^"]*)" exists$""") { (username: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  When("""^I go to the "([^"]*)" page$""") { (pageName: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  Then("""^I should see "([^"]*)"$""") { (excpectedText: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }
  
}