package controllers

import play.api._
import play.api.mvc._

import models._

trait Authentication { self: Controller =>

  case class AuthenticatedRequest(
    user: User, private val request: Request[AnyContent]) extends WrappedRequest(request)

  case class OptAuthenticatedRequest(
    maybeUser: Option[User], private val request: Request[AnyContent]) extends WrappedRequest(request)

  def Authenticated(f: AuthenticatedRequest => Result) = {
    Action { request =>
      val result = for {
        username <- fetchUsername()
        user <- User.findBy("username", username)
      } yield f(AuthenticatedRequest(user, request))
      result getOrElse Unauthorized
    }
  }

  def OptAuthenticated(f: OptAuthenticatedRequest => Result) = {
    Action { request =>
      val maybeUser = for {
        username <- fetchUsername()
        user <- User.findBy("username", username)
      } yield (user)
      f(OptAuthenticatedRequest(maybeUser, request))
    }
  }

  private def fetchUsername() = {
    Play.current.configuration.getString("fake.login.username")
  }

}