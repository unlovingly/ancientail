package com.example.manything.outsiders.play.components

import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import router.Routes

import com.example.manything.ambientendre.outsiders.play.components.{
  ProductComponents,
  PublisherComponents
}
import com.example.manything.ancientail.outsiders.play.components.{
  ShopComponents,
  SlipComponents
}

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with OutsiderComponents
  with ProductComponents
  with PublisherComponents
  with ShopComponents
  with SlipComponents
  with FiltersComponents
  with controllers.AssetsComponents {
  override lazy val router: Routes =
    new Routes(httpErrorHandler,
               productRoutes,
               publisherRoutes,
               shopRoutes,
               slipRoutes,
               assets)
}
