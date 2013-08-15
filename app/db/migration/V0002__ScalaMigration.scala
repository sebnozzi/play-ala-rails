package db.migration

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection
import daos.UserDao
import play.Logger

class V0002__ScalaMigration extends JdbcMigration {

  override def migrate(connection: Connection) {
    def insertUserName(username: String) {
      val statement =
        connection.prepareStatement(s"INSERT INTO user (username) VALUES ('$username')");
      try {
        statement.execute();
      } finally {
        statement.close();
      }
    }
    //insertUserName("Asterix")
    //insertUserName("Obelix")
    Logger.debug("Did Scala migration")
  }

}