package com.example.manything.ancientail.domain.usecases.shop

import scala.util.Try

import cats.MonadError

import com.example.manything.ancientail.domain.models.shop._
import com.example.manything.ancientail.domain.models.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipRepository
}
import com.example.manything.ancientail.domain.models.slip.sales.{
  SalesSlip,
  SalesSlipRepository
}

class ShopUseCases[A[_]](val shops: ShopRepository[A],
                         val salesSlips: SalesSlipRepository[A],
                         val purchaseSlips: PurchaseSlipRepository[A])
  extends CreateShop[A]
  with ShowShops[A]
  with ShowStocks[A]
  with Selling[A]
  with Storing[A] {
  override def sell(slip: SalesSlip)(
    implicit ME: MonadError[A, Throwable]): A[Shop] = {
    import cats.implicits.toFlatMapOps

    // 1. 商品在庫をとる
    // 2. 出庫処理をとる (在庫数を変更する)
    // 3. 保存する
    val id = slip.items.map(_.pluCode)
    val shop = shops.retrieveWithStocksBy(slip.senderId, id)

    shop.flatMap { s =>
      ME.fromTry(Try {
          s.outbound(slip)
        })
        .flatMap { l =>
          shops.store(l)
          // TODO 本当は Shop を集約ルートにすべき 時間あるときやる
          //  slips を shops に注入するのがいいんじゃないか
          salesSlips.store(slip)
        }
    }

    shop
  }

  override def store(slip: PurchaseSlip)(
    implicit ME: MonadError[A, Throwable]): A[Shop] = {
    import cats.implicits.toFlatMapOps

    val id = slip.items.map(i => PluCode.generate(v = i.productId, a = i.price))
    val shop = shops.retrieveWithStocksBy(slip.receiverId, id)

    shop.flatMap { s =>
      ME.fromTry(Try {
          s.inbound(slip)
        })
        .flatMap { l =>
          shops.store(l)
          // TODO 本当は Shop を集約ルートにすべき 時間あるときやる
          //  slips を shops に注入するのがいいんじゃないか
          purchaseSlips.store(slip)
        }
    }

    shop
  }
}
