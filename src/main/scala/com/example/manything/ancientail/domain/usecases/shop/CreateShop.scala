package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop.Shop

trait CreateShop[A[_]] { this: ShopUseCases[A] =>
  def create(s: Shop): A[Shop] = shops.store(s)
}
