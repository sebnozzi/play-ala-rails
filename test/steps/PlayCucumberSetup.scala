package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import testhelpers.PlayCucumberEnvironment


class SetupSteps extends ScalaDsl with EN  {

  Before() { s =>
    PlayCucumberEnvironment.init()
  }

  After() { s =>
    PlayCucumberEnvironment.shutdown()
  }

}