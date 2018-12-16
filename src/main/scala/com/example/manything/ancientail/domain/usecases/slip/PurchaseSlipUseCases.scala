package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.ancientail.domain.models.shop.ShopRepository
import com.example.manything.ancientail.domain.models.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipRepository
}

class PurchaseSlipUseCases[A[_]](val shops: ShopRepository[A],
                                 val slips: PurchaseSlipRepository[A])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[A] {
  override type EntityType = PurchaseSlip
}
