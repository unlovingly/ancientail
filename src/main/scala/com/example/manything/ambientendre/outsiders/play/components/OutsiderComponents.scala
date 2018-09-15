package com.example.manything.ambientendre.outsiders.play.components

import play.api.db.slick._
import play.filters.HttpFiltersComponents
import slick.basic.{BasicProfile, DatabaseConfig}
import slick.jdbc.{JdbcBackend, JdbcProfile}

trait OutsiderComponents extends HttpFiltersComponents with SlickComponents {
  lazy val databaseConfigProvider: DatabaseConfigProvider =
    new DatabaseConfigProvider {
      def get[P <: BasicProfile]: DatabaseConfig[P] =
        slickApi.dbConfig[P](DbName("default"))
    }
  implicit lazy val db: JdbcBackend#DatabaseDef =
    databaseConfigProvider.get[JdbcProfile].db
}
