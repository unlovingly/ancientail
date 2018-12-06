package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipRepository
}

class PurchaseSlipUseCases(val shops: ShopRepository[EitherTFuture],
                           val slips: PurchaseSlipRepository[EitherTFuture])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[EitherTFuture]
  with StoringProducts[EitherTFuture] {
  override type EntityType = PurchaseSlip

  override def storing(slip: PurchaseSlip): EitherTFuture[PurchaseSlip] = {
    import cats.implicits.catsStdInstancesForFuture

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
