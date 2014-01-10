package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.Matchers

import testhelpers.PlayCucumberSupport

import cucumber.api.DataTable
import scala.collection.JavaConversions._

import controllers.FakeAuthenticator

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._
import play.api.Logger

import models.{ User, Post }


class UserAuthenticationSteps extends ScalaDsl with EN with Matchers with PlayCucumberSupport {

  Given("""^that user "([^"]*)" exists$""") { (username: String) =>
    User(username).create
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