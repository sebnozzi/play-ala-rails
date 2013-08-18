package controllers

import play.api._
import play.api.mvc._
import daos.UserDao
import daos.PostDao

object UserActions extends Controller {
  
  def index = Action {
    val users = UserDao.findAll()
    Ok(views.html.users.index(users))
  }
  
  def posts(userId: Long) = Action {
    val user = UserDao.findById(userId).get
    val posts = PostDao.findAllForUser(user)
    Ok(views.html.users.posts(user, posts))
  }
  
}