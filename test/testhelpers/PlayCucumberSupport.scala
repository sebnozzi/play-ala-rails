package testhelpers

import play.api.test._
import play.api.test.Helpers._
import org.openqa.selenium.WebDriver

import cucumber.api.scala.{ ScalaDsl, EN }

trait PlayCucumberSupport { self: ScalaDsl with EN =>

  protected def server: TestServer = PlayCucumberEnvironment.server
  protected def browser: TestBrowser = PlayCucumberEnvironment.browser
  protected def driver: WebDriver = PlayCucumberEnvironment.driver

  protected def port = PlayCucumberEnvironment.port

  Before {
    PlayCucumberEnvironment.init()
  }

  After {
    PlayCucumberEnvironment.shutdown()
  }

}

