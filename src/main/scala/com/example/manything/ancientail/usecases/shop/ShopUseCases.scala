package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop.ShopRepository

class ShopUseCases(implicit val shops: ShopRepository[EitherTFuture])
  extends CreateShop
  with ListingShops
