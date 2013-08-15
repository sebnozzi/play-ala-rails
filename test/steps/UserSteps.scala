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
  
  Given("""^that user "([^"]*)" wrote the following posts$""") { (username: String, table: DataTable) =>
    val user = UserDao.findByUsername(username).get
    table.flatten().foreach{ postText => 
      PostDao.createPost(user, postText)
    }
  }
  
}