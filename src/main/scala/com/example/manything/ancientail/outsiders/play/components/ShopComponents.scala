package com.example.manything.ancientail.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.outsiders.infrastructure.shop.ShopRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.api.v1.shop.ShopController
import com.example.manything.ancientail.domain.usecases.shop.ShopUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents

trait ShopComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val shopRepository: ShopRepository[EitherTFuture] =
    new ShopRepositoryWithSlick(db = db)
  private lazy val shopUseCases =
    new ShopUseCases(shops = shopRepository)

  lazy val shopController =
    new ShopController(cc = controllerComponents, shopUseCases = shopUseCases)(
      executionContext)
  lazy val shopRoutes =
    new shops.Routes(httpErrorHandler, shopController)
}
