package controllers

import play.api._
import play.api.mvc._

import models._

object FakeAuthenticator {

  private var _username: Option[String] = None

  def logout() = {
    _username = None
  }

  def loggedInUsername() = _username

  def loginAs(username: String) {
    _username = Some(username)
  }

}

trait Authentication { self: Controller =>

  case class AuthenticatedRequest(
    user: User, private val request: Request[AnyContent]) extends WrappedRequest(request)

  case class OptAuthenticatedRequest(
    maybeUser: Option[User], private val request: Request[AnyContent]) extends WrappedRequest(request)

  def Authenticated(f: AuthenticatedRequest => Result) = {
    Action { request =>
      val result = for {
        username <- fetchUsername(request)
        user <- User.findBy("username", username)
      } yield f(AuthenticatedRequest(user, request))
      result getOrElse Unauthorized
    }
  }

  def OptAuthenticated(f: OptAuthenticatedRequest => Result) = {
    Action { request =>
      val maybeUser = for {
        username <- fetchUsername(request)
        user <- User.findBy("username", username)
      } yield (user)
      f(OptAuthenticatedRequest(maybeUser, request))
    }
  }

  def LoginAndRedirect(username: String, call: Call)(implicit request: Request[AnyContent]) = {
    if (Play.isTest(Play.current)) {
      FakeAuthenticator.loginAs(username)
      Redirect(call)
    } else {
      Redirect(call).
        withSession(session + ("username" -> username))
    }
  }

  def LogoutAndRedirect(call: Call)(implicit request: Request[AnyContent]) = {
    if (Play.isTest(Play.current)) {
      FakeAuthenticator.logout()
      Redirect(call)
    } else {
      Redirect(call).
        withSession(session - "username")
    }
  }

  private def fetchUsername(request: Request[AnyContent]) = {
    if (Play.isTest(Play.current)) {
      FakeAuthenticator.loggedInUsername
    } else {
      request.session.get("username")
    }
  }

}