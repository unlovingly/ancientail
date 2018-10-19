package com.example.manything.ancientail.usecases.shop

import com.example.manything.ancientail.domain.shop.ShopRepository

import scala.concurrent.Future

class ShopUseCases(implicit val shops: ShopRepository[Future])
  extends CreateShop
  with ListingShops
