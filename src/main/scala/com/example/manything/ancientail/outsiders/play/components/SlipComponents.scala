package com.example.manything.ancientail.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlipRepository
import com.example.manything.ancientail.domain.slip.sales.SalesSlipRepository
import com.example.manything.ancientail.outsiders.infrastructure.shop.ShopRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.exchange.ExchangeSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.purchase.PurchaseSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.sales.SalesSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip.SlipController
import com.example.manything.ancientail.usecases.slip.SlipUseCases
import com.example.manything.outsiders.play.components.OutsiderComponents

trait SlipComponents {
  this: BuiltInComponentsFromContext with OutsiderComponents =>
  private lazy val shopRepository: ShopRepository[EitherTFuture] =
    new ShopRepositoryWithSlick(db = db)
  private lazy val exchangeSlipRepository
    : ExchangeSlipRepository[EitherTFuture] =
    new ExchangeSlipRepositoryWithSlick(db = db)
  private lazy val purchaseSlipRepository
    : PurchaseSlipRepository[EitherTFuture] =
    new PurchaseSlipRepositoryWithSlick(db = db)
  private lazy val salesSlipRepository: SalesSlipRepository[EitherTFuture] =
    new SalesSlipRepositoryWithSlick(db = db)
  private lazy val slipUseCases =
    new SlipUseCases(shops = shopRepository,
                     exchangeSlips = exchangeSlipRepository,
                     purchaseSlips = purchaseSlipRepository,
                     salesSlips = salesSlipRepository)

  lazy val slipController =
    new SlipController(cc = controllerComponents, slipUseCases = slipUseCases)(
      executionContext)
  lazy val slipRoutes =
    new slips.Routes(httpErrorHandler, slipController)
}
