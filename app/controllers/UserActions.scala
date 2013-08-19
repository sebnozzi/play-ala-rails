package controllers

import play.api._
import play.api.mvc._

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._

import models._

object UserActions extends Controller {

  def index = Action {
    val users: List[User] = User.toList
    Ok(views.html.users.index(users))
  }

  def posts(userId: Long) = Action {
    User.find(userId).map { user =>
      val posts = user.posts.orderBy(_.createdAt desc).toList
      Ok(views.html.users.posts(user, posts))
    } getOrElse {
      BadRequest("user not found")
    }
  }

}