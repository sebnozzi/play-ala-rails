package models

import org.squeryl.dsl.OneToMany

case class User(username: String) extends db.BaseEntity {
  
  lazy val posts: OneToMany[Post] = AppSchema.userToPosts.left(this)
  
}