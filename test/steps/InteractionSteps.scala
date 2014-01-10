package steps

import play.api._
import play.api.mvc._
import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.Matchers
import testhelpers.PlayCucumberSupport

import org.openqa.selenium._
import org.fluentlenium.core.filter.FilterConstructor._


class InteractionSteps extends ScalaDsl with EN with Matchers with PlayCucumberSupport {

  When("""^I click on "([^"]*)"$""") { (linkLabel: String) =>
    val element = driver.findElement(new By.ByPartialLinkText(linkLabel))
    element.click()
  }  
  
  When("""^I type "([^"]*)" in the "([^"]*)" field$""") { (text: String, fieldName: String) =>
    browser.fill("*", withName(fieldName)).`with`(text)
  }

  And("""^press "([^"]*)"$""") { (buttonLabel: String) =>
    val button = browser.find("button", withText(buttonLabel))
    button.click()
  }  
  
}