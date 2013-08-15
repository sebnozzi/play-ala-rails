package daos

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import models._

class UserDaoSpec extends Specification {

  "UserDao" should {
    "create an user" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val user: User = UserDao.createUser(username = "manager")
        assert(user.username === "manager")
      }
    }
  }

}