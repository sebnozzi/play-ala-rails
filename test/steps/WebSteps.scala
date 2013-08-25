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

class WebSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  def baseUrl = s"http://localhost:$port"

  When("""^I go to the "([^"]*)" page$""") { (pageName: String) =>
    val pageUrl = pageName match {
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
    elements should have length expectedAmountOfPosts
  }

  When("""^I click on "([^"]*)"$""") { (username: String) =>
    val startUrl = driver.getCurrentUrl()
    val element = driver.findElement(new By.ByPartialLinkText(username))
    browser.waitUntil {
      element.click()
      driver.getCurrentUrl() != startUrl
    }
  }

  Then("""^I should be on the posts page of "([^"]*)"$""") { (username: String) =>
    val user = User.findBy("username", username).get
    val expectedUrl = controllers.routes.UserActions.posts(user.id).url
    (driver.getCurrentUrl()) should endWith(expectedUrl)
  }

  Then("""^the post nr. (\d+) should start with "([^"]*)"$""") { (postNr: Int, text: String) =>
    val postsList = driver.findElement(new By.ByClassName("posts"))
    val postListItems = postsList.findElements(new By.ByTagName("li"))
    val postListItem = postListItems.get(postNr - 1)
    postListItem.getText() should startWith(text)
  }

  When("""^I'm in the log-in page$""") { () =>
    val pageUrl = controllers.routes.Application.logIn.url
    browser.goTo(baseUrl + pageUrl)
  }

  When("""^I type "([^"]*)" in the "([^"]*)" field$""") { (text: String, fieldName: String) =>
    browser.fill("*", withName(fieldName)).`with`(text)
  }

  When("""^press "([^"]*)"$""") { (buttonLabel: String) =>
    val button = browser.find("button", withText(buttonLabel))
    button.click()
  }

}