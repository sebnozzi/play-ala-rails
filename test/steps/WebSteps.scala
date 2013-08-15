package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers

import testhelpers.PlayCucumberSupport

import play.api._
import play.api.mvc._

class WebSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  When("""^I go to the "([^"]*)" page$""") { (pageName: String) =>
    val baseUrl = s"http://localhost:$port"
    val pageUrl: String = pageName match {
      case "user list" => controllers.routes.UserActions.index().url
      case _ => throw new RuntimeException("unsupported page")
    }
    browser.goTo(baseUrl + pageUrl)
  }

  Then("""^I should see "([^"]*)"$""") { (expectedText: String) =>
    driver.getPageSource() should include(expectedText)
  }

}