package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.purchase.PurchaseSlip

/**
 * 入庫ユースケース
 */
trait StoringProducts { this: SlipUseCases =>

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def storing(slip: PurchaseSlip): EitherAppliedFuture[SlipBase] = {
    val productIds = slip.items.map(_.productId)
    val shop = shops.retrieveWithStocks(slip.receiverId, productIds)
    // 1. 伝票を保存して
    val result = purchaseSlips.store(slip)

    shop.map { s =>
      // 2. 在庫情報を更新する
      s.map { h =>
        val o = h.storing(slip)

        shops.store(o)
      }
    }

    result
  }
}
