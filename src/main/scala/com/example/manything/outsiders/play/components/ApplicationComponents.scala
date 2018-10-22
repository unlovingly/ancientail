package com.example.manything.outsiders.play.components

import com.example.manything.ambientendre.outsiders.play.components.{
  ProductComponents,
  PublisherComponents
}
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.filters.HttpFiltersComponents
import router.Routes

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with OutsiderComponents
  with PublisherComponents
  with ProductComponents
  with HttpFiltersComponents
  with controllers.AssetsComponents {
  override lazy val router: Routes =
    new Routes(httpErrorHandler, publisherRoutes, productRoutes, assets)
}
