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
    import scala.concurrent.ExecutionContext.Implicits.global

    val shop = shops.retrieve(Seq(shopId))
    // 1. 伝票を保存して
    val result = slips.store(slip)

    // TODO: N+1
    shop.map { s =>
      s.map { h =>
        // 2. 在庫情報を更新する
        h.map { o =>
          o.storing(slip)

          shops.store(o)
        }
      }
    }

    result
  }
}
