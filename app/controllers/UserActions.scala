package controllers

import play.api._
import play.api.mvc._

object UserActions extends Controller {
  
  def index = Action {
    Ok(views.html.users.index())
  }
  
}