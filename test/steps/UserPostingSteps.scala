package steps

import cucumber.api.scala.{ ScalaDsl, EN }
import org.scalatest.matchers.ShouldMatchers

import testhelpers.PlayCucumberSupport

import cucumber.api.DataTable
import scala.collection.JavaConversions._

import controllers.FakeAuthenticator

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._
import play.api.Logger

import models.{ User, Post }


class UserPostingSteps extends ScalaDsl with EN with ShouldMatchers with PlayCucumberSupport {

  Given("""^that user "([^"]*)" posted$""") { (username: String, datatable: DataTable) =>
    val user = User.findBy("username", username).get
    val postsAndTimeAgo = datatable.flatten().toList.grouped(2)
    postsAndTimeAgo.drop(1) // drop headers
    postsAndTimeAgo.foreach { case List(text, timeAgo) =>
      user.posts << Post(text)
    }
  }

  Then("""^user "([^"]*)" should have a post with "([^"]*)"$""") { (username: String, postText: String) =>
    val user = User.findBy("username", username).get
    user.posts.exists( post => post.text === postText) should be(true)
  }



}