package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop.{Shop, ShopId}

trait ShowShops[A[_]] { this: ShopUseCases[A] =>
  def retrieve(): A[Seq[Shop]] = shops.retrieve()
  def retrieve(shopId: ShopId): A[Shop] = shops.retrieve(shopId)
}
