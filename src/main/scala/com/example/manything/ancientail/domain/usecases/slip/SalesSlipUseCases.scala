package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.ancientail.domain.models.shop.ShopRepository
import com.example.manything.ancientail.domain.models.slip.sales.{
  SalesSlip,
  SalesSlipRepository
}

class SalesSlipUseCases[A[_]](val shops: ShopRepository[A],
                              val slips: SalesSlipRepository[A])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[A] {
  override type EntityType = SalesSlip
}
