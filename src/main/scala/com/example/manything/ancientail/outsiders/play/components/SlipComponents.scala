package com.example.manything.ancientail.outsiders.play.components

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlipRepository
import com.example.manything.ancientail.outsiders.infrastructure.shop.ShopRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.exchange.ExchangeSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.purchase.PurchaseSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip.SlipController
import com.example.manything.ancientail.usecases.slip.SlipUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents
import play.api.BuiltInComponentsFromContext

trait SlipComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  implicit private lazy val shopRepository
    : ShopRepository[EitherAppliedFuture] =
    new ShopRepositoryWithSlick()
  implicit private lazy val exchangeSlipRepository
    : ExchangeSlipRepository[EitherAppliedFuture] =
    new ExchangeSlipRepositoryWithSlick()
  implicit private lazy val purchaseSlipRepository
    : PurchaseSlipRepository[EitherAppliedFuture] =
    new PurchaseSlipRepositoryWithSlick()
  private lazy val slipUseCases =
    new SlipUseCases()

  lazy val slipController =
    new SlipController(cc = controllerComponents, slipUseCases = slipUseCases)(
      executionContext)
  lazy val slipRoutes =
    new slips.Routes(httpErrorHandler, slipController)
}
