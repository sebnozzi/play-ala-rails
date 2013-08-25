package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers
import testhelpers.PlayCucumberSupport
import models.{ User, Post }
import cucumber.api.DataTable
import scala.collection.JavaConversions._
import cucumber.api.PendingException
import controllers.FakeAuthenticator
import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._
import play.api.Logger


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

  Then("""^user "([^"]*)" should have a post with "([^"]*)"$""") { (username: String, postText: String) =>
    val user = User.findBy("username", username).get
    user.posts.exists( post => post.text === postText) should be(true)
  }

  Given("""^no user is logged-in$""") { () =>
    FakeAuthenticator.logout()
  }

  Then("""^the logged-in user should be "([^"]*)"$""") { (username: String) =>
    FakeAuthenticator.loggedInUsername should equal(Some(username))
  }

  Given("""^I'm logged-in as "([^"]*)"$""") { (username: String) =>
    FakeAuthenticator.loginAs(username)
  }

  Then("""^no user should be logged-in$""") { () =>
    FakeAuthenticator.loggedInUsername should equal(None)
  }

}