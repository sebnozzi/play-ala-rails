package models

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val users = table[User]("users")
  val posts = table[Post]("posts")
}

case class User(username:String) extends ActiveRecord {
  lazy val posts = hasMany[Post]
}

case class Post(text:String) extends ActiveRecord {
  val userId:Option[Int] = None
  lazy val user = belongsTo[User]
}

object User extends ActiveRecordCompanion[User]
object Post extends ActiveRecordCompanion[Post]
