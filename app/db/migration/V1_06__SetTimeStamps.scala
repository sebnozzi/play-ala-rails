package db.migration

import com.googlecode.flyway.core.api.migration.jdbc.JdbcMigration
import java.sql.Connection

import com.github.aselab.activerecord.{ views => aviews, _ }
import dsl._

import models.{User, Post}


class V1_06__SetTimeStamps extends JdbcMigration {

  override def migrate(ignoredConnection: Connection) {
    
    
    var ts = new java.util.Date().getTime()
    Post.orderBy(_.id asc).toIterator.foreach{ post => 
      post.createdAt = new java.sql.Timestamp(ts)
      post.updatedAt = new java.sql.Timestamp(ts)
      post.save
      ts += 5000
    }
  }

}