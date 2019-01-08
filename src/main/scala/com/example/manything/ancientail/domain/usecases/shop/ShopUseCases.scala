package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop._

class ShopUseCases[A[_]](val shops: ShopRepository[A])
    extends CreateShop[A]
    with ShowShops[A]
    with ShowStocks[A] {
  override def create(s: Shop): A[Shop] = shops.store(s)
  override def retrieve(): A[Seq[Shop]] = shops.retrieve()
  override def retrieve(shopId: ShopId): A[Shop] = shops.retrieve(shopId)
  override def retrieveWithStocksBy(q: String): A[Seq[Shop]] =
    shops.retrieveWithStocksBy(q)

  override def retrieveWithStocksBy(shopId: ShopId): A[Shop] = {
    shops.retrieveWithStocksBy(shopId)
  }

  override def retrieveWithStocksBy(shopId: ShopId, code: PluCode): A[Shop] = {
    shops.retrieveWithStocksBy(shopId, code)
  }
}
