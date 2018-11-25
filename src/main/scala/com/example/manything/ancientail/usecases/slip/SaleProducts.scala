package com.example.manything.ancientail.usecases.slip

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.slip.sales.SalesSlip

/**
 * 詳細をみるときのユースケース
 */
trait SaleProducts { this: SlipUseCases =>
  def sell(slip: SalesSlip): EitherTFuture[SalesSlip] = {
    import cats.implicits._

    val productIds = slip.items.map(_.productId)
    val shop = shops.retrieveWithStocks(slip.receiverId, productIds)

    val result = salesSlips.store(slip)

    shop.map { s =>
      val h = s.outbound(slip)

      shops.store(h)
    }

    result
  }
}
