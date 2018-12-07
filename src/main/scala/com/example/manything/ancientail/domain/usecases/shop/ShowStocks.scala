package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop._

trait ShowStocks[A[_]] { this: ShopUseCases[A] =>
  def retrieveWithStocksBy(q: String): A[Seq[Shop]] =
    shops.retrieveWithStocksBy(q)

  def retrieveWithStocksBy(shopId: ShopId): A[Shop] = {
    shops.retrieveWithStocksBy(shopId)
  }

  def retrieveWithStocksBy(shopId: ShopId, code: PluCode): A[Shop] = {
    shops.retrieveWithStocksBy(shopId, code)
  }
}
