package com.example.manything.ancientail.domain.models.shop

import cats.{Eq, Semigroup}

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip.{Amount, Price}

/**
 * 店舗在庫
 *
 * @param pluCode PluCode
 * @param shopId ShopId 在庫を抱える店舗
 * @param productId ProductId 製品情報
 * @param amount Amount 数量
 * @param price Price 価格
 */
case class Stock(
  pluCode: PluCode,
  shopId: ShopId,
  productId: ProductId,
  amount: Amount,
  price: Price
) {
  def +(operand: Stock): Stock = {
    import cats.syntax.eq.catsSyntaxEq

    if (this === operand)
      this.copy(amount = amount + operand.amount)
    else
      this
  }

  def -(operand: Stock): Stock = {
    import cats.syntax.eq.catsSyntaxEq

    require(amount >= operand.amount)

    if (this === operand)
      this.copy(amount = amount - operand.amount)
    else
      this
  }
}

object Stock {
  implicit lazy val stockEq: Eq[Stock] =
    (x: Stock, y: Stock) => {
      import cats.syntax.eq.catsSyntaxEq

      x.pluCode === y.pluCode
    }

  implicit lazy val stockSemigroup: Semigroup[Stock] =
    (x: Stock, y: Stock) => {
      import cats.syntax.eq.catsSyntaxEq

      if (x === y)
        x + y
      else
        x
    }
}
