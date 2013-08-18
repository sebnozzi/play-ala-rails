package db.migration

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import daos.UserDao

class V1_02__CreateSomeUsers extends JdbcMigration {

  override def migrate(ignoredConnection: Connection) {
    UserDao.createUser("Homer")
    UserDao.createUser("Smithers")
  }

}