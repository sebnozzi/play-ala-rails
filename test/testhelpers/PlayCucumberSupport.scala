package testhelpers

import play.api.test._
import play.api.test.Helpers._
import org.openqa.selenium.WebDriver

import cucumber.api.scala.{ ScalaDsl, EN }

trait PlayCucumberSupport { self: ScalaDsl with EN =>

  protected lazy val server: TestServer = PlayCucumberEnvironment.server
  protected lazy val browser: TestBrowser = PlayCucumberEnvironment.browser
  protected lazy val driver: WebDriver = PlayCucumberEnvironment.driver

  protected lazy val port = PlayCucumberEnvironment.seleniumPort

  Before {
    PlayCucumberEnvironment.init()
  }

  After {
    PlayCucumberEnvironment.shutdown()
  }

}

