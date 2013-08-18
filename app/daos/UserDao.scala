package daos

import models._

import com.github.aselab.activerecord._
import dsl._

object UserDao {

  def createUser(username: String): User = {
    User(username).create
  }

  def findByUsername(username: String): Option[User] = {
    User.findBy("username", username)
  }

  def findById(userId: Long): Option[User] = {
    User.find(userId)
  }

  def findAll(): List[User] = {
    User.all.toList
  }

}