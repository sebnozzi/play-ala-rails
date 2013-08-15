package testhelpers

import play.api.Play

import play.api.test.FakeApplication

object PlayAppEnvironment extends AppTesting {

  private var initialized: Boolean = false
  var fakeApp:FakeApplication = _

  def init(startApplication:Boolean=true) {
    synchronized {
      if (!initialized) {
        fakeApp = appWithInMemoryDb()
        if(startApplication)
          Play.start(fakeApp)
        initialized = true
      }
    }
  }

  def shutdown(stopApplication:Boolean=true) {
    synchronized {
      if (initialized) {
        if(stopApplication)
          Play.stop()
        initialized = false
      }
    }
  }

}