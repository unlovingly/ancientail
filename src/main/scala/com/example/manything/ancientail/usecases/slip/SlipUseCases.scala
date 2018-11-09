package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlipRepository

import scala.concurrent.ExecutionContext

class SlipUseCases(
  implicit val shops: ShopRepository[EitherAppliedFuture],
  implicit val exchangeSlips: ExchangeSlipRepository[EitherAppliedFuture],
  implicit val purchaseSlips: PurchaseSlipRepository[EitherAppliedFuture],
  implicit val executionContext: ExecutionContext)
  extends RetrieveSlips
  with RetrieveOneSlip
  with StoringProducts
  with ExchangeProducts
