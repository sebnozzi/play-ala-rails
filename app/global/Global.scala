package global

import play.api.db.DB
import play.api.GlobalSettings
import play.api.Play
import play.api.Play.current
import play.api.Application
import com.googlecode.flyway.core.Flyway
import play.api.Logger
import com.github.aselab.activerecord._
import com.github.aselab.activerecord.ActiveRecordConfig
import models.Tables

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    if (Play.isTest) {
      printSchemaGeneration()
      Tables.initialize
    } else {
      val config = Map(
        "driver" -> app.configuration.getString("db.default.driver").get,
        "jdbcurl" -> app.configuration.getString("db.default.url").get,
        "username" -> app.configuration.getString("db.default.user").get,
        "password" -> app.configuration.getString("db.default.pass").get)
      Tables.initialize(config)
      migrateDb(app)
    }
  }

  def printSchemaGeneration() {
    import org.squeryl.{ Session, SessionFactory }
    import com.github.aselab.activerecord.dsl._
    import com.github.aselab.activerecord.squeryl.Implicits._
    inTransaction {
      Tables.printDdl(str => Logger.debug(str))
    }
  }

  def migrateDb(app: Application) {
    val flyway = new Flyway()

    val url = app.configuration.getString("db.default.url").get
    val user = app.configuration.getString("db.default.user").get
    val password = app.configuration.getString("db.default.pass").get

    flyway.setDataSource(url, user, password)
    flyway.setInitOnMigrate(true)
    flyway.migrate()
  }

}