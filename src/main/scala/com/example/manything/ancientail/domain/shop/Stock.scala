package com.example.manything.ancientail.domain.shop

import cats.{Eq, Semigroup}
import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip.{Amount, Price}

case class Stock(
  pluCode: PluCode,
  shopId: ShopId,
  productId: ProductId,
  amount: Amount,
  price: Price
)

object Stock {
  import cats.implicits._

  implicit lazy val stockEq: Eq[Stock] =
    (x: Stock, y: Stock) => {
      x.pluCode === y.pluCode
    }

  implicit lazy val stockSemigroup: Semigroup[Stock] =
    (x: Stock, y: Stock) => {
      if (x === y)
        x.copy(amount = x.amount + y.amount)
      else
        x
    }
}
