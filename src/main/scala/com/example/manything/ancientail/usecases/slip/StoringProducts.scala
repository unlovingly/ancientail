package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.Slip

/**
 * 入庫ユースケース
 */
trait StoringProducts { this: SlipUseCases =>

  /**
   * 1. 伝票を保存して
   * 2. 在庫情報を更新する
   */
  def storing(shopId: ShopId, slip: Slip): EitherAppliedFuture[Slip] = {
    val productIds = slip.items.map { i =>
      i.productId
    }
    val shop = shops.retrieveWithStock(shopId, productIds)
    // 1. 伝票を保存して
    val result = slips.store(slip)

    shop.map { s =>
      s.map { h =>
        // 2. 在庫情報を更新する
        h.map { o =>
          val p = o.storing(slip)

          shops.store(p)
        }
      }
    }

    result
  }
}
