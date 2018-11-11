package com.example.manything.ancientail.domain.shop

import cats.{Eq, Semigroup}

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip.{Amount, Price}

/**
 * 店舗在庫
 * @param pluCode PluCode Product Look UP Code, 商品と価格のペアになる。プライマリキーに相当する
 * @param shopId ShopId 在庫を抱える店舗
 * @param productId ProductId 商品情報
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
    import cats.implicits._

    if (this === operand)
      this.copy(amount = amount + operand.amount)
    else
      this
  }

  def -(operand: Stock): Stock = {
    import cats.implicits._

    require(amount >= operand.amount)

    if (this === operand)
      this.copy(amount = amount - operand.amount)
    else
      this
  }
}

object Stock {
  import cats.implicits._

  implicit lazy val stockEq: Eq[Stock] =
    (x: Stock, y: Stock) => {
      x.pluCode === y.pluCode
    }

  implicit lazy val stockSemigroup: Semigroup[Stock] =
    (x: Stock, y: Stock) => {
      if (x === y)
        x + y
      else
        x
    }
}
