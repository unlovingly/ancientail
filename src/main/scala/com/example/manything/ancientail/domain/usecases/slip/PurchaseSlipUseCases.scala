package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import cats.MonadError

import com.example.manything.ancientail.domain.models.shop.{
  PluCode,
  ShopRepository
}
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
    implicit ME: MonadError[A, Throwable]): A[PurchaseSlip] = {
    import cats.implicits.toFlatMapOps

    val productIds =
      slip.items.map(i => PluCode.generate(v = i.productId, a = i.price))
    val shop = shops.retrieveWithStocksBy(slip.receiverId, productIds)

    val result = shop.flatMap { s =>
      val target = s.inbound(slip)

      shops.store(target)
      slips.store(slip)
    }

    result
  }
}
