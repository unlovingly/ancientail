package com.example.manything.outsiders.play.components

import play.api.db.slick._
import slick.basic.{BasicProfile, DatabaseConfig}
import slick.jdbc.{JdbcBackend, JdbcProfile}

trait OutsiderComponents extends SlickComponents {
  lazy val databaseConfigProvider: DatabaseConfigProvider =
    new DatabaseConfigProvider {
      def get[P <: BasicProfile]: DatabaseConfig[P] =
        slickApi.dbConfig[P](DbName("default")) // TODO
    }
  implicit lazy val db: JdbcBackend#DatabaseDef =
    databaseConfigProvider.get[JdbcProfile].db
}
