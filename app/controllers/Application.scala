package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User

object Application extends Controller with Authentication {

  case class LoginFields(username: String)

  val userForm = Form[LoginFields](
    mapping(
      "username" -> nonEmptyText)(LoginFields.apply)(LoginFields.unapply))

  def index = TODO

  def logInPage = Action {
    Ok(views.html.login(userForm))
  }

  def logIn = Action { implicit request =>
    userForm.bindFromRequest().fold(
      formWithErrors => {
        BadRequest(views.html.login(formWithErrors)).flashing("error" -> "User missing")
      },
      loginFields => {
        val username = loginFields.username
        User.findBy("username", username).map { user =>
          logInAs(username)
          Redirect(routes.UserActions.posts(user.id))
        } getOrElse {
          BadRequest(views.html.login(userForm.fill(loginFields))).flashing("error" -> "User not found")
        }
      })
  }

}