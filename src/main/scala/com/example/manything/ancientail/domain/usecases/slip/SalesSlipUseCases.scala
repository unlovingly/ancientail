package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext

import cats.Functor

import com.example.manything.ancientail.domain.models.shop.ShopRepository
import com.example.manything.ancientail.domain.models.slip.sales.{
  SalesSlip,
  SalesSlipRepository
}

class SalesSlipUseCases[A[_]](val shops: ShopRepository[A],
                              val slips: SalesSlipRepository[A])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[A]
  with SellProducts[A] {
  override type EntityType = SalesSlip

  override def sell(slip: SalesSlip)(implicit F: Functor[A]): A[SalesSlip] = {
    import cats.syntax.functor.toFunctorOps

    val productIds = slip.items.map(_.productId)
    val shop = shops.retrieveWithStocksBy(slip.senderId, productIds)

    // 逐次処理しなければいけない？
    val result = slips.store(slip)

    shop.map { s =>
      val h = s.outbound(slip)

      shops.store(h)
    }

    result
  }
}
