package testhelpers

import play.api.Play

import play.api.test.FakeApplication

object PlayAppEnvironment extends AppTesting {

  private var initialized: Boolean = false
  var fakeApp:FakeApplication = _

  def init() {
    synchronized {
      if (!initialized) {
        fakeApp = appWithInMemoryDb()
        Play.start(fakeApp)
        initialized = true
      }
    }
  }

  def shutdown() {
    synchronized {
      if (initialized) {
        Play.stop()
        initialized = false
      }
    }
  }

}