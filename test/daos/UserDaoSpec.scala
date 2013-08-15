package daos

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import models._
import org.specs2.execute.Result
import org.specs2.execute.AsResult

class UserDaoSpec extends Specification {

  def appWithInMemoryDb() = FakeApplication(additionalConfiguration = inMemoryDatabase())

  "UserDao" should {
    "create an user" in {
      running(appWithInMemoryDb) {
        val user: User = UserDao.createUser(username = "manager")
        assert(user.username === "manager")
      }
    }


  }

}