package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.Shop

trait CreateShop { this: ShopUseCases =>
  def create(s: Shop): EitherAppliedFuture[Shop] = shops.store(s)
}
