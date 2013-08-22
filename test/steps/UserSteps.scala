package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import testhelpers.PlayCucumberSupport
import models.{ User, Post }
import cucumber.api.DataTable
import scala.collection.JavaConversions._

class UserSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  Given("""^that user "([^"]*)" exists$""") { (username: String) =>
    User(username).create
  }

  Given("""^that user "([^"]*)" posted$""") { (username: String, datatable: DataTable) =>
    val user = User.findBy("username", username).get
    val posts = datatable.flatten().toList
    posts.foreach { text =>
      user.posts << Post(text)
    }
  }

}