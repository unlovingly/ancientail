package com.example.manything.ancientail.domain.usecases.shop

import com.example.manything.ancientail.domain.models.shop._

class ShopUseCases[A[_]](val shops: ShopRepository[A])
  extends CreateShop[A]
  with ShowShops[A]
  with ShowStocks[A]
