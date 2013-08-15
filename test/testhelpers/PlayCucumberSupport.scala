package testhelpers

import cucumber.api.scala.{ ScalaDsl, EN }

trait PlayCucumberSupport { self: ScalaDsl with EN =>

  Before {
    PlayAppEnvironment.init()
  }

  After {
    PlayAppEnvironment.shutdown()
  }

}

