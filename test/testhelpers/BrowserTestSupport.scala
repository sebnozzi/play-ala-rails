package testhelpers

import play.api.test._
import play.api.test.Helpers._
import org.openqa.selenium.WebDriver

import cucumber.api.scala.{ ScalaDsl, EN }



trait BrowserTestSupport { self: ScalaDsl with EN =>

  protected lazy val server: TestServer = BrowserTestEnvironment.server
  protected lazy val browser: TestBrowser = BrowserTestEnvironment.browser
  protected lazy val driver: WebDriver = BrowserTestEnvironment.driver

  protected lazy val port = BrowserTestEnvironment.seleniumPort
  
  Before {
    BrowserTestEnvironment.init()
  }

  After {
    BrowserTestEnvironment.shutdown()
  }

}
