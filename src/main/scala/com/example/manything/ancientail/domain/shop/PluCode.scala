package com.example.manything.ancientail.domain.shop

import cats.Eq
import com.example.manything.ambientendre.domain.product.ProductId

case class PluCode(value: String) {}

object PluCode {
  implicit lazy val pluCodeEq: Eq[PluCode] = (x: PluCode, y: PluCode) => {
    import cats.implicits._

    x.value === y.value
  }

  def generate(v: ShopId, a: ProductId, l: Int): PluCode = {
    new PluCode(v.value.toString + a.value.toString + l.toString)
  }
}
