package com.example.manything.outsiders.play.components

import com.example.manything.ambientendre.outsiders.play.components.PublisherComponents
import com.example.manything.ancientail.outsiders.play.components.ProductComponents
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.db.slick._
import play.filters.HttpFiltersComponents
import router.Routes
import slick.basic.{BasicProfile, DatabaseConfig}
import slick.jdbc.{JdbcBackend, JdbcProfile}

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with OutsiderComponents
  with PublisherComponents
  with ProductComponents
  with HttpFiltersComponents
  with SlickComponents
  with controllers.AssetsComponents {
  override lazy val router: Routes =
    new Routes(httpErrorHandler, publisherRoutes, productRoutes, assets)
}
