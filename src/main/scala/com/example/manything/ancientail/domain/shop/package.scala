package com.example.manything.ancientail.domain

import java.util.UUID

import cats.kernel.Semigroup
import com.example.manything.roundelayout.domain.Identifiability

package object shop {
  type ShopId = Identifiability[UUID, Shop]
  type StockId = Identifiability[UUID, Stock]

  implicit lazy val stockSemigroup = new Semigroup[Stock] {
    override def combine(x: Stock, y: Stock): Stock = x.productId match {
      case y.productId => x.copy(amount = x.amount + y.amount)
      case _ => x
    }
  }
}
