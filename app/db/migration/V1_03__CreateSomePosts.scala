package db.migration

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import daos._

class V1_03__CreateSomePosts extends JdbcMigration {

  override def migrate(ignoredConnection: Connection) {
    UserDao.findByUsername("Homer").map { homer =>
      PostDao.createPost(homer, "I'm hungry")
      PostDao.createPost(homer, "I should go to Moe's")
      PostDao.createPost(homer, "Or order some Pizza")
    }

  }

}