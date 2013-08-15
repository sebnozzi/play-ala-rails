package models

import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._

object AppSchema extends Schema {
  val users = table[User]("users")
  val posts = table[Post]("posts")

  val userToPosts = oneToManyRelation(users, posts).
    via((user, post) => user.id === post.userId)

}