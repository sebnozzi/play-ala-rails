package testhelpers

import play.api.test._
import play.api.test.Helpers._
import play.api.Play
import play.api.Play.current
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import play.Logger

object PlayCucumberEnvironment {

  private var _server: TestServer = _
  private var _browser: TestBrowser = _
  private var _driver: WebDriver = _
  private var _seleniumPort: Int = _

  private var initialized: Boolean = false

  def browser = _browser
  def driver = _driver
  def server = _server
  def port = _seleniumPort

  def init() {
    synchronized {
      if (!initialized) {
        initialized = true
        val app = FakeApplication(additionalConfiguration = inMemoryDatabase())
        Logger.info("started fake app")

        _seleniumPort = app.configuration.getInt("selenium.port").getOrElse(3333)
        _server = TestServer(_seleniumPort, app)
        _server.start
        Logger.info("started test server")

        val headlessMode = app.configuration.getBoolean("cucumber.headless").getOrElse(true)

        if (headlessMode) {
          // PhantomJS has to be installed and added to the operating system PATH
          val cap = new DesiredCapabilities()
          _driver = new PhantomJSDriver(cap)
          _browser = TestBrowser(_driver, None)
        } else {
          _browser = TestBrowser.of(classOf[ChromeDriver])
          _driver = _browser.getDriver
        }
        Logger.info("started test browser")
      }
    }
  }

  def shutdown() {
    synchronized {
      if (initialized) {
        if(_server != null) _server.stop
        Logger.info("stopped test server")
        if(_browser != null) _browser.quit()
        Logger.info("stopped test browser")
        Play.stop()
        Logger.info("stopped fake app")
        initialized = false
      }
    }
  }

}