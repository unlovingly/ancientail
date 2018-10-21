package com.example.manything.ancientail.usecases.shop

import com.example.manything.ancientail.domain.shop.Shop

import scala.concurrent.Future

trait CreateShop { this: ShopUseCases =>
  def create(s: Shop): Future[Shop] = shops.store(s)
}
