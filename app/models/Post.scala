package models

import models.db.BaseEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.ManyToOne

case class Post(userId:Long, text:String) extends BaseEntity {

  lazy val user: ManyToOne[User] = AppSchema.userToPosts.right(this)
  
}