package com.example.manything.ancientail.domain.shop

import java.util.UUID

import cats.{Eq, Semigroup}
import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip.{Amount, Price}
import com.example.manything.roundelayout.domain.Identifiability

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

    // require(amount >= operand.amount)

    if (this === operand)
      this.copy(amount = amount - operand.amount)
    else
      this
  }
}

case class StockId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = Stock
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
