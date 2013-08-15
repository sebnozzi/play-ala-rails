package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import cucumber.runtime.PendingException

class WebSteps extends ScalaDsl with EN with ShouldMatchers {

  When("""^I go to the "([^"]*)" page$""") { (pageName: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }

  Then("""^I should see "([^"]*)"$""") { (excpectedText: String) =>
    //// Express the Regexp above with the code you wish you had
    throw new PendingException()
  }

}