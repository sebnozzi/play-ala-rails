package global

import play.api.db.DB
import play.api.GlobalSettings
import play.api.Play
import play.api.Play.current
import play.api.Application
import play.api.Logger
import com.googlecode.flyway.core.Flyway
import com.github.aselab.activerecord._
import com.github.aselab.activerecord.ActiveRecordConfig
import models.Tables

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    if (Play.isTest) {
      Tables.initialize
    } else {
      val config = Map(
        "driver" -> app.configuration.getString("db.default.driver").get,
        "jdbcurl" -> app.configuration.getString("db.default.url").get,
        "username" -> app.configuration.getString("db.default.user").get,
        "password" -> app.configuration.getString("db.default.pass").get)
      /* NOTE: schema is generated only if completely empty
         In production it won't be generated. Use migrations for that. */
      Tables.initialize(config)
      migrateDb(app)
    }

    if (shouldPrintSchemaGeneration(app)) {
      printSchemaGenerationSQL(app)
    }
  }

  override def onStop(app: Application) {
    if (Play.isTest) {
      dsl.inTransaction {
        Tables.drop
        Tables.cleanup
      }
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

  def shouldPrintSchemaGeneration(app: Application) =
    app.configuration.getBoolean("activerecord.schemageneration.printSQL").getOrElse(false)

  def printSchemaGenerationSQL(app: Application) {
    import org.squeryl.{ Session, SessionFactory }
    import com.github.aselab.activerecord.dsl._
    import com.github.aselab.activerecord.squeryl.Implicits._
    inTransaction {
      Logger.debug("Schema generation SQL:")
      Tables.printDdl(str => Logger.debug(str))
    }
  }

}