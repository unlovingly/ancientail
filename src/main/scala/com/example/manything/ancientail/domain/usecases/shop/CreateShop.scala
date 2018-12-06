package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.Shop

trait CreateShop { this: ShopUseCases =>
  def create(s: Shop): EitherTFuture[Shop] = shops.store(s)
}
