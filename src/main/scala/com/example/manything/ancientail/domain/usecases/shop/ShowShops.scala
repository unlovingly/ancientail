package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop.Shop

trait ShowShops[A[_]] { this: ShopUseCases[A] =>
  def retrieve(): A[Seq[Shop]] = shops.retrieve()
}
