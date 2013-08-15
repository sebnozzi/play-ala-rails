package controllers

import play.api._
import play.api.mvc._
import daos.UserDao

object UserActions extends Controller {
  
  def index = Action {
    val users = UserDao.findAll()
    Ok(views.html.users.index(users))
  }
  
}