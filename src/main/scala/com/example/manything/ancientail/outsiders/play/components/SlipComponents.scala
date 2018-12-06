package com.example.manything.ancientail.outsiders.play.components

import play.api.BuiltInComponentsFromContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.shop.ShopRepository
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlipRepository
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlipRepository
import com.example.manything.ancientail.domain.usecases.slip._
import com.example.manything.ancientail.outsiders.infrastructure.shop.ShopRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.exchange.ExchangeSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.purchase.PurchaseSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.infrastructure.slip.sales.SalesSlipRepositoryWithSlick
import com.example.manything.ancientail.outsiders.play.controllers.api.v1.slip.{
  ExchangeSlipController,
  PurchaseSlipController,
  SalesSlipController
}
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

  private lazy val exchangeSlipUseCases =
    new ExchangeSlipUseCases(shops = shopRepository,
                             slips = exchangeSlipRepository)

  private lazy val purchaseSlipUseCases =
    new PurchaseSlipUseCases(shops = shopRepository,
                             slips = purchaseSlipRepository)

  private lazy val salesSlipUseCases =
    new SalesSlipUseCases(shops = shopRepository, slips = salesSlipRepository)

  lazy val exchangeSlipController =
    new ExchangeSlipController(
      cc = controllerComponents,
      slipUseCases = exchangeSlipUseCases)(executionContext)
  lazy val purchaseSlipController =
    new PurchaseSlipController(
      cc = controllerComponents,
      slipUseCases = purchaseSlipUseCases)(executionContext)
  lazy val salesSlipController =
    new SalesSlipController(cc = controllerComponents,
                            slipUseCases = salesSlipUseCases)(executionContext)

  lazy val exchangeSlipsRoutes =
    new exchangeSlips.Routes(httpErrorHandler, exchangeSlipController)
  lazy val purchaseSlipsRoutes =
    new purchaseSlips.Routes(httpErrorHandler, purchaseSlipController)
  lazy val salesSlipsRoutes =
    new salesSlips.Routes(httpErrorHandler, salesSlipController)

  lazy val slipRoutes =
    new slips.Routes(httpErrorHandler,
                     exchangeSlipsRoutes,
                     purchaseSlipsRoutes,
                     salesSlipsRoutes)
}
