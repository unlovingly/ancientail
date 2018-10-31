package com.example.manything.ancientail.domain

import java.util.UUID

import cats.kernel.Semigroup
import com.example.manything.roundelayout.domain.Identifiability

package object shop {
  type ShopId = Identifiability[UUID, Shop]
  type StockId = Identifiability[UUID, Stock]

  implicit lazy val stockSemigroup: Semigroup[Stock] = (x: Stock, y: Stock) => {
    import cats.implicits._

    if (x.shopId === y.shopId && x.productId === y.productId && x.price === y.price)
      x.copy(amount = x.amount + y.amount)
    else
      x
  }
}
