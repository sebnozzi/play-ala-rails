package models

import org.squeryl.Schema

object AppSchema extends Schema {
  val users     = table[User]("user")
}