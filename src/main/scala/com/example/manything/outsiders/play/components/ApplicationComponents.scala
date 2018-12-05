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
import com.example.manything.blessedict.outsiders.play.components.CustomerComponents

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with OutsiderComponents
  with CustomerComponents
  with ProductComponents
  with PublisherComponents
  with ShopComponents
  with SlipComponents
  with FiltersComponents
  with controllers.AssetsComponents {
  override lazy val router: Routes =
    new Routes(httpErrorHandler,
               customerRoutes,
               productRoutes,
               publisherRoutes,
               shopRoutes,
               slipRoutes,
               assets)
}
