package com.example.manything.outsiders.play.components

import slick.basic.{ BasicProfile, DatabaseConfig }
import slick.jdbc.{ JdbcBackend, JdbcProfile }

import play.api.db.slick._

trait OutsiderComponents extends SlickComponents {
  lazy val databaseConfigProvider: DatabaseConfigProvider =
    new DatabaseConfigProvider {
      def get[P <: BasicProfile]: DatabaseConfig[P] =
        slickApi.dbConfig[P](DbName("default")) // TODO
    }
  implicit lazy val db: JdbcBackend#DatabaseDef =
    databaseConfigProvider.get[JdbcProfile].db
}
