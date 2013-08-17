package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import testhelpers.PlayCucumberSupport
import daos.UserDao
import cucumber.api.DataTable
import scala.collection.JavaConversions._
import daos.PostDao

class UserSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  Given("""^that user "([^"]*)" exists$""") { (username: String) =>
    UserDao.createUser(username)
  }

  Given("""^that user "([^"]*)" posted "([^"]*)"$""") { (username: String, text: String) =>
    val user = UserDao.findByUsername(username).get
    PostDao.createPost(user, text)
  }

}