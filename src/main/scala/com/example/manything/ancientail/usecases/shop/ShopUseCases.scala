package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherAppliedFuture
import com.example.manything.ancientail.domain.shop.ShopRepository

class ShopUseCases(implicit val shops: ShopRepository[EitherAppliedFuture])
  extends CreateShop
  with ListingShops
