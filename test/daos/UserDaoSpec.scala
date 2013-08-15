package daos

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._

import testhelpers.AppTesting

import models._

class UserDaoSpec extends Specification with AppTesting {

  "UserDao" should {
    "create an user" in {
      running(appWithInMemoryDb) {
        val user: User = UserDao.createUser(username = "manager")
        assert(user.username === "manager")
      }
    }

    "persist user when creating it" in {
      running(appWithInMemoryDb) {
        val user: User = UserDao.createUser(username = "manager")
        assert(user.isPersisted)
      }
    }

    "retrieve an user" in new WithDbData {
      def prepareDbWithData() {
        UserDao.createUser(username = "manager")
      }
      val user = UserDao.findByUsername("manager").get
      assert(user.username === "manager")
    }

    "have persisted users when retrieving" in new WithDbData {
      def prepareDbWithData() {
        UserDao.createUser(username = "manager")
      }
      val user = UserDao.findByUsername("manager").get
      assert(user.isPersisted)
    }

  }

}