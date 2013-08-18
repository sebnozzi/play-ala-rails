package db.migration

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection

import models.{User, Post}


class V1_03__CreateSomePosts extends JdbcMigration {

  override def migrate(ignoredConnection: Connection) {
    User.findBy("username", "Homer").map { homer =>
      homer.posts << Post("I'm hungry")
      homer.posts << Post("I should go to Moe's")
      homer.posts << Post("Or order some Pizza")
    }
  }

}