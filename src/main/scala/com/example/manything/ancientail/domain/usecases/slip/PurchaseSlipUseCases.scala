package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext
import scala.util.Try

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
  with Storing[A] {
  override type EntityType = PurchaseSlip
  override def store(slip: PurchaseSlip)(
    implicit ME: MonadError[A, Throwable]): A[PurchaseSlip] = {
    import cats.implicits.toFlatMapOps

    val id = slip.items.map(i => PluCode.generate(v = i.productId, a = i.price))
    val shop = shops.retrieveWithStocksBy(slip.receiverId, id)

    shop.flatMap { s =>
      ME.fromTry(Try {
          s.storing(slip)
        })
        .flatMap { l =>
          shops.store(l)
          slips.store(slip)
        }
    }
  }
}
