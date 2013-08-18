package steps

import play.api._
import play.api.mvc._

import cucumber.api.scala.{ ScalaDsl, EN }

import org.scalatest.matchers.ShouldMatchers
import testhelpers.PlayCucumberSupport

import org.openqa.selenium.By

import models._


class WebSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  def baseUrl = s"http://localhost:$port"

  When("""^I go to the "([^"]*)" page$""") { (pageName: String) =>
    val pageUrl: String = pageName match {
      case "user list" => controllers.routes.UserActions.index().url
      case _ => throw new RuntimeException("unsupported page")
    }
    browser.goTo(baseUrl + pageUrl)
  }

  Then("""^I should see "([^"]*)"$""") { (expectedText: String) =>
    driver.getPageSource() should include(expectedText)
  }

  When("""^I go to the posts page of user "([^"]*)"$""") { (username: String) =>
    val user = User.findBy("username", username).get
    val pageUrl = controllers.routes.UserActions.posts(user.id).url
    browser.goTo(baseUrl + pageUrl)
  }
  
  Then("""^I should see (\d+) posts$""") { (expectedAmountOfPosts: Int) =>
    val elements = driver.findElements(new By.ByClassName("post"))
    assert(elements.size() === expectedAmountOfPosts)
  }

}