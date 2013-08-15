import org.squeryl.adapters.{ H2Adapter, MySQLAdapter }
import org.squeryl.internals.DatabaseAdapter
import org.squeryl.{ Session, SessionFactory }
import play.api.db.DB
import play.api.GlobalSettings

import play.api.Application

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    initDbSession(app)
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