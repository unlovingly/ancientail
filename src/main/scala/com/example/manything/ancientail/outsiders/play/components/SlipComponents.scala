package com.example.manything.ancientail.outsiders.play.components

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.SlipRepository
import com.example.manything.ancientail.outsiders.infrastructure.shop.ShopRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip.SlipController
import com.example.manything.ancientail.usecases.slip.SlipUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents
import play.api.BuiltInComponentsFromContext

trait SlipComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val shopRepository
    : ShopRepository[EitherAppliedFuture] =
    new ShopRepositoryWithSlick()
  implicit private lazy val slipRepository
    : SlipRepository[EitherAppliedFuture] =
    new SlipRepositoryWithSlick()
  private lazy val slipUseCases =
    new SlipUseCases()

  lazy val slipController =
    new SlipController(cc = controllerComponents, slipUseCases = slipUseCases)(
      executionContext)
  lazy val slipRoutes =
    new slips.Routes(httpErrorHandler, slipController)
}
