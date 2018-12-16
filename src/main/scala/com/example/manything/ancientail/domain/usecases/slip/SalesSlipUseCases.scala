package com.example.manything.ancientail.domain.usecases.slip

import scala.concurrent.ExecutionContext
import scala.util.Try

import cats.MonadError

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

  override def sell(slip: SalesSlip)(
    implicit ME: MonadError[A, Throwable]): A[SalesSlip] = {
    import cats.implicits.toFlatMapOps

    val productIds = slip.items.map(_.pluCode)
    val shop = shops.retrieveWithStocksBy(slip.senderId, productIds)

    // TODO 副作用起きまくりなので IO に変える
    val result = shop.flatMap { s =>
      ME.fromTry(Try {
          s.outbound(slip)
        })
        .flatMap { l =>
          shops.store(l)
          // TODO 本当は Shop を集約ルートにすべき 時間あるときやる
          //  slips を shops に注入するのがいいんじゃないか
          slips.store(slip)
        }
    }

    result
  }
}
