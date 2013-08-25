package steps

import play.api._
import play.api.mvc._
import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import testhelpers.PlayCucumberSupport
import org.openqa.selenium.By
import models._
import java.util.concurrent.TimeUnit
import cucumber.api.PendingException

import org.fluentlenium.core.filter.FilterConstructor._

class PageNavigationSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  def baseUrl = s"http://localhost:$port"

  When("""^I go to the "([^"]*)" page$""") { (pageName: String) =>
    val pageUrl = pageName match {
      case "user list" => controllers.routes.UserActions.index().url
      case _ => throw new RuntimeException("unsupported page")
    }
    browser.goTo(baseUrl + pageUrl)
  }
  
  When("""^I'm in the log-in page$""") { () =>
    val pageUrl = controllers.routes.Application.logIn.url
    browser.goTo(baseUrl + pageUrl)
  }
  
  When("""^I go to the posts page of user "([^"]*)"$""") { (username: String) =>
    val user = User.findBy("username", username).get
    val pageUrl = controllers.routes.UserActions.posts(user.id).url
    browser.goTo(baseUrl + pageUrl)
  }

  Then("""^I should see "([^"]*)"$""") { (expectedText: String) =>
    driver.getPageSource() should include(expectedText)
  }  
  
}