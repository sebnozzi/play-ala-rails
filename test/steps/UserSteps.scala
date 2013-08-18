package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers

import testhelpers.PlayCucumberSupport

import models.{ User, Post }

class UserSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  Given("""^that user "([^"]*)" exists$""") { (username: String) =>
    User(username).create
  }

  Given("""^that user "([^"]*)" posted "([^"]*)"$""") { (username: String, text: String) =>
    val user = User.findBy("username", username).get
    user.posts << Post(text)
  }

}