package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._

object Application extends Controller with Authentication {

  case class LoginFields(username: String)

  val userForm = Form[LoginFields](
    mapping(
      "username" -> text.
        verifying("Missing user", !_.isEmpty()).
        verifying("Invalid user", username => checkExists(username))
    )(LoginFields.apply)(LoginFields.unapply))

  def checkExists(username: String) = {
    User.exists(user => user.username === username)
  }

  def index = TODO

  def logInPage = Action { implicit r =>
    Ok(views.html.login(userForm))
  }

  def logIn = Action { implicit request =>
    userForm.bindFromRequest().fold(
      formWithErrors => {
        
        val errorMsg = formWithErrors("username").error.get.message
        
        BadRequest(views.html.login(formWithErrors))
      },
      loginFields => {
        val username = loginFields.username
        val user = User.findBy("username", username).get
        logInAs(username)
        Redirect(routes.UserActions.posts(user.id))
      })
  }

  def doLogOut() = Authenticated { implicit r =>
    logOut()
    Redirect(routes.UserActions.index())
  }

}