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
      Ok(views.html.users.posts(user, posts, r.maybeUser))
    } getOrElse {
      BadRequest("user not found")
    }
  }

  case class PostData(text: String)

  val form = Form(
    mapping(
      "post" -> text)(PostData.apply)(PostData.unapply))

  def addPost(userId: Long) = Authenticated { implicit r =>
    if (r.user.id == userId) {
      val boundForm = form.bindFromRequest
      if(!boundForm.hasErrors){
        val postText = boundForm.get.text
        User.find(userId).map { user => user.posts << Post(postText) }
      } else {
        Logger.debug("Input error")
      }
      Redirect(routes.UserActions.posts(userId))
    } else {
      BadRequest("invalid user")
    }
  }

}