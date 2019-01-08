package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.ancientail.domain.models.shop.ShopRepository
import com.example.manything.ancientail.domain.models.slip.exchange.{
  ExchangeSlip,
  ExchangeSlipRepository
}

class ExchangeSlipUseCases[A[_]](
    val shops: ShopRepository[A],
    val slips: ExchangeSlipRepository[A]
)(implicit val executionContext: ExecutionContext)
    extends SlipUseCases[A] {
  override type EntityType = ExchangeSlip
}
