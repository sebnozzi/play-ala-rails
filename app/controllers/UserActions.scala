package controllers

import play.api._
import play.api.mvc._

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._

import models._

object UserActions extends Controller with Authentication {

  def index = Action {
    val users: List[User] = User.toList
    Ok(views.html.users.index(users))
  }

  def posts(userId: Long) = OptAuthenticated { implicit r =>
    User.find(userId).map { user =>
      val posts = user.posts.orderBy(_.createdAt desc).toList
      val isLoggedInUser = r.maybeUser.exists(_ == user)
      Ok(views.html.users.posts(user, posts, isLoggedInUser))
    } getOrElse {
      BadRequest("user not found")
    }
  }

  def addPost(userId: Long) = Authenticated { implicit r =>
    if (r.user.id == userId) {
      Redirect(routes.UserActions.posts(userId))
    } else {
      BadRequest("invalid user")
    }
  }

}