package steps.setup

import cucumber.api.scala.{ ScalaDsl, EN }
import testhelpers.PlayCucumberEnvironment


class PlayCucumberSetup extends ScalaDsl with EN  {

  Before() { s =>
    PlayCucumberEnvironment.init()
  }

  After() { s =>
    PlayCucumberEnvironment.shutdown()
  }

}