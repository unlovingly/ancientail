package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.models.shop._

trait ShowStocks { this: ShopUseCases =>
  def retrieveWithStocksBy(q: String): EitherTFuture[Seq[Shop]] =
    shops.retrieveWithStocksBy(q)

  def retrieveWithStocksBy(shopId: ShopId): EitherTFuture[Shop] = {
    shops.retrieveWithStocksBy(shopId)
  }

  def retrieveWithStocksBy(shopId: ShopId,
                           code: PluCode): EitherTFuture[Shop] = {
    shops.retrieveWithStocksBy(shopId, code)
  }
}
