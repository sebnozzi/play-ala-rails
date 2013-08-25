package steps

import play.api._
import play.api.mvc._
import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import testhelpers.PlayCucumberSupport

import org.openqa.selenium._
import org.fluentlenium.core.filter.FilterConstructor._


class InteractionSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  When("""^I click on "([^"]*)"$""") { (username: String) =>
    val startUrl = driver.getCurrentUrl()
    val element = driver.findElement(new By.ByPartialLinkText(username))
    element.click()
  }  
  
  When("""^I type "([^"]*)" in the "([^"]*)" field$""") { (text: String, fieldName: String) =>
    browser.fill("*", withName(fieldName)).`with`(text)
  }

  When("""^press "([^"]*)"$""") { (buttonLabel: String) =>
    val button = browser.find("button", withText(buttonLabel))
    button.click()
  }  
  
}