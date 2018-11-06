package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.{ShopId, ShopRepository}
import com.example.manything.ancientail.domain.slip._

/**
 * 入庫ユースケース
 */
trait ExchangingProducts { this: SlipUseCases =>

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def exchanging(slip: ExchangeSlip)(
    implicit shops: ShopRepository[EitherAppliedFuture],
    slips: SlipRepository[ShopId, EitherAppliedFuture])
    : EitherAppliedFuture[Slip[_]] = {
    val productIds = slip.items.map(_.productId)
    val sender = shops.retrieveWithStocks(slip.senderId, productIds)
    val receiver = shops.retrieveWithStocks(slip.receiverId, productIds)
    val result = slips.store(slip)

    receiver.map { s =>
      // 2. 在庫情報を更新する
      s.map { h =>
        val o = h.inbound(slip)

        shops.store(o)
      }
    }

    result
  }
}
