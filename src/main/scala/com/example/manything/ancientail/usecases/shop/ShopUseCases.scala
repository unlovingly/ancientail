package com.example.manything.ancientail.usecases.shop

import com.example.manything.EitherTFuture
import com.example.manything.ancientail.domain.shop._

class ShopUseCases(val shops: ShopRepository[EitherTFuture])
  extends CreateShop
  with ListingShops
  with ShowStocks
