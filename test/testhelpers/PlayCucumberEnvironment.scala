package testhelpers

import play.api.test._
import play.api.test.Helpers._
import play.api.Play
import play.api.Play.current


import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities


object PlayCucumberEnvironment {

  var server: TestServer = _
  var browser: TestBrowser = _
  var driver: WebDriver = _
  var seleniumPort: Int = _

  private var initialized: Boolean = false

  def init() {
    synchronized {
      if (!initialized) {
        initialized = true
        val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
        
        seleniumPort = app.configuration.getInt("selenium.port").getOrElse(3333)
        server = TestServer(seleniumPort, app)
        server.start

        val headlessMode = app.configuration.getBoolean("cucumber.headless").getOrElse(true)

        browser = TestBrowser.of(classOf[ChromeDriver])
        driver = browser.getDriver
      }
    }
  }

  def shutdown() {
    synchronized {
      if (initialized) {
        server.stop
        browser.quit()
        initialized = false
      }
    }
  }

}