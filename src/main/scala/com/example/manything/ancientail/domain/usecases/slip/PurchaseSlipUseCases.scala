package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import cats.Functor

import com.example.manything.ancientail.domain.models.shop.ShopRepository
import com.example.manything.ancientail.domain.models.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipRepository
}

class PurchaseSlipUseCases[A[_]](val shops: ShopRepository[A],
                                 val slips: PurchaseSlipRepository[A])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[A]
  with StoringProducts[A] {
  override type EntityType = PurchaseSlip

  override def storing(slip: PurchaseSlip)(
    implicit F: Functor[A]): A[PurchaseSlip] = {
    import cats.syntax.functor.toFunctorOps

    val productIds = slip.items.map(_.productId)
    val shop = shops.retrieveWithStocksBy(slip.receiverId, productIds)
    // 1. 伝票を保存して
    val result = slips.store(slip)

    shop.map { s =>
      val h = s.inbound(slip)

      shops.store(h)
    }

    result
  }
}
