package com.example.manything.ancientail.usecases.slip

import scala.concurrent.ExecutionContext

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository
import com.example.manything.ancientail.domain.slip.sales.{
  SalesSlip,
  SalesSlipRepository
}

class SalesSlipUseCases(val shops: ShopRepository[EitherTFuture],
                        val slips: SalesSlipRepository[EitherTFuture])(
  implicit val executionContext: ExecutionContext)
  extends SlipUseCases[EitherTFuture]
  with SellProducts[EitherTFuture] {
  override type EntityType = SalesSlip

  override def sell(slip: SalesSlip): EitherTFuture[SalesSlip] = {
    import cats.implicits.catsStdInstancesForFuture

    val productIds = slip.items.map(_.productId)
    val shop = shops.retrieveWithStocks(slip.senderId, productIds)

    // 逐次処理しなければいけない？
    val result = slips.store(slip)

    shop.map { s =>
      val h = s.outbound(slip)

      shops.store(h)
    }

    result
  }
}
