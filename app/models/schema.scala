package models

import com.github.aselab.activerecord._
import dsl._


object Tables extends ActiveRecordTables {
  val users = table[User]("users")
  val posts = table[Post]("posts")
}

case class User(var username: String) extends ActiveRecord {
  lazy val posts = hasMany[Post]
}

case class Post(var text: String) extends ActiveRecord with Timestamps {
  var userId: Long = _
  lazy val user = belongsTo[User]

  import helpers.PrettyTimeFormatter._

  def prettyTime: String = {
    val optTimestamp = {
      Seq(Option(updatedAt), Option(createdAt)).flatten.headOption
    }
    optTimestamp.map { ts =>
      formatter.format(new java.util.Date(ts.getTime()))
    } getOrElse ("")

  }

}


object User extends ActiveRecordCompanion[User]
object Post extends ActiveRecordCompanion[Post]
