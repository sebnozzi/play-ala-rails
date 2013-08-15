package daos

import models._

object UserDao {

  def createUser(username: String): User = User(username)

}