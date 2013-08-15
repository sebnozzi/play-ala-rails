import play.api.db.DB
import play.api.GlobalSettings

import play.api.Play
import play.api.Play.current
import play.api.Application

import org.squeryl.adapters.{ H2Adapter, MySQLAdapter }
import org.squeryl.internals.DatabaseAdapter
import org.squeryl.{ Session, SessionFactory }

import models.AppSchema
import org.squeryl.PrimitiveTypeMode._

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    initDbSession(app)
    if (Play.isTest) {
      createDbSchema()
    }
  }

  def createDbSchema() {
    inTransaction {
      AppSchema.create
    }
  }

  def initDbSession(app: Application) {
    SessionFactory.concreteFactory = app.configuration.getString("db.default.driver") match {
      case Some("org.h2.Driver") => Some(() => getSession(new H2Adapter, app))
      case Some("com.mysql.jdbc.Driver") => Some(() => getSession(new MySQLAdapter, app))
      case _ => sys.error("Database driver not supported - Configure here")
    }
  }

  def getSession(adapter: DatabaseAdapter, app: Application) = Session.create(DB.getConnection()(app), adapter)

}