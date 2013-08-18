package db.migration

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection

import models.User


class V1_02__CreateSomeUsers extends JdbcMigration {

  override def migrate(ignoredConnection: Connection) {
    User("Homer").create
    User("Smithers").create
  }

}