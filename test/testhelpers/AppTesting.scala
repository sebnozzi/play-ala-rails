package testhelpers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.specs2.execute.AsResult

trait AppTesting {

  def appWithInMemoryDb() = FakeApplication(additionalConfiguration = inMemoryDatabase())

  abstract class WithDbData extends WithApplication(app = appWithInMemoryDb) {
    override def around[T](t: => T)(implicit evidence: AsResult[T]) = super.around {
      prepareDbWithData()
      t
    }
    def prepareDbWithData()
  }

}