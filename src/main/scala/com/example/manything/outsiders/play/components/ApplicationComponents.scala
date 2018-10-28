package com.example.manything.outsiders.play.components

import com.example.manything.ambientendre.outsiders.play.components.{
  ProductComponents,
  PublisherComponents
}
import com.example.manything.ancientail.outsiders.play.components.ShopComponents
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.filters.HttpFiltersComponents
import router.Routes

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with OutsiderComponents
  with ProductComponents
  with PublisherComponents
  with ShopComponents
  with HttpFiltersComponents
  with controllers.AssetsComponents {
  override lazy val router: Routes =
    new Routes(httpErrorHandler,
               productRoutes,
               publisherRoutes,
               shopRoutes,
               assets)
}
