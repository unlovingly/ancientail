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
  with Selling[A] {
  override type EntityType = SalesSlip

  override def sell(slip: SalesSlip)(
    implicit ME: MonadError[A, Throwable]): A[SalesSlip] = {
    import cats.implicits.toFlatMapOps

    // 1. 商品在庫をとる
    // 2. 出庫処理をとる (在庫数を変更する)
    // 3. 保存する
    val id = slip.items.map(_.pluCode)
    val shop = shops.retrieveWithStocksBy(slip.senderId, id)

    shop.flatMap { s =>
      ME.fromTry(Try {
          s.sell(slip)
        })
        .flatMap { l =>
          shops.store(l)
          // TODO 本当は Shop を集約ルートにすべき 時間あるときやる
          //  slips を shops に注入するのがいいんじゃないか
          //  このままだとトランザクション境界が壊れている
          slips.store(slip)
        }
    }
  }
}
