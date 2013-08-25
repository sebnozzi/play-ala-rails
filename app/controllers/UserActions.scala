package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._

import models._

object UserActions extends Controller with Authentication {

  def index() = OptAuthenticated { implicit r =>
    val users: List[User] = User.toList
    Ok(views.html.users.index(users, r.maybeUser))
  }

  def posts(userId: Long) = OptAuthenticated { implicit r =>
    User.find(userId).map { user =>
      val posts = user.posts.orderBy(_.createdAt desc).toList
      Ok(views.html.users.posts(user, posts, form, r.maybeUser))
    } getOrElse {
      BadRequest("user not found")
    }
  }

  case class PostData(text: String)

  val form = Form(
    mapping(
      "post" -> text.verifying("Text required", msg => !msg.trim().isEmpty()))(PostData.apply)(PostData.unapply))

  def addPost(userId: Long) = Authenticated { implicit r =>
    if (r.user.id == userId) {
      val boundForm = form.bindFromRequest
      val user = User.find(userId).get
      if (!boundForm.hasErrors) {
        val postText = boundForm.get.text
        user.posts << Post(postText)
        Redirect(routes.UserActions.posts(userId))
      } else {
        val posts = user.posts.orderBy(_.createdAt desc).toList
        BadRequest(views.html.users.posts(user, posts, boundForm, Some(user)))
      }
    } else {
      BadRequest("invalid user")
    }
  }

}