package com.example.manything.ancientail.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.exchange.ExchangeSlipRepository
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlipRepository

class SlipUseCases(
  implicit val shops: ShopRepository[EitherTFuture],
  implicit val exchangeSlips: ExchangeSlipRepository[EitherTFuture],
  implicit val purchaseSlips: PurchaseSlipRepository[EitherTFuture],
  implicit val executionContext: ExecutionContext)
  extends RetrieveSlips
  with RetrieveOneSlip
  with StoringProducts
  with ExchangeProducts
