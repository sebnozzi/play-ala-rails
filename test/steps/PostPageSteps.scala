package steps

import play.api._
import play.api.mvc._
import cucumber.api.scala.{ ScalaDsl, EN }
import testhelpers.PlayCucumberSupport
import org.openqa.selenium._
import org.fluentlenium.core.filter.FilterConstructor._
import models._
import org.scalatest.Matchers


class PostPageSteps extends ScalaDsl with EN with Matchers with PlayCucumberSupport {

  Then("""^I should be on the posts page of "([^"]*)"$""") { (username: String) =>
    val user = User.findBy("username", username).get
    val expectedUrl = controllers.routes.UserActions.posts(user.id).url
    driver.getCurrentUrl() should endWith(expectedUrl)
  }
  
  Then("""^I should see (\d+) posts$""") { (expectedAmountOfPosts: Int) =>
    val elements = driver.findElements(new By.ByClassName("post"))
    elements should have length expectedAmountOfPosts
  }

  Then("""^the post nr. (\d+) should contain "([^"]*)"$""") { (postNr: Int, text: String) =>
    val postsList = driver.findElement(new By.ByClassName("posts"))
    val postListItems = postsList.findElements(new By.ByTagName("li"))
    val postListItem = postListItems.get(postNr - 1)
    postListItem.getText() should include(text)
  }  
  
}