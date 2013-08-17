package daos

import org.squeryl.PrimitiveTypeMode._

import models._
import AppSchema._


object UserDao {

  def createUser(username: String): User = inTransaction {
    users.insert(new User(username))
  }

  def findByUsername(username:String):Option[User] = inTransaction {
    users.find(user => user.username == username)
  }
  
  def findById(userId:Long):Option[User] = inTransaction {
    users.find(user => user.id == userId)
  }
  
  def findAll():List[User] = inTransaction {
    users.seq.toList
  }
  
}