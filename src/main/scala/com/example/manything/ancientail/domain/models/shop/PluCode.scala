package com.example.manything.ancientail.domain.models.shop

import java.util.UUID

import cats.Eq

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip.Price

/**
 * Price Look Up Code, 製品情報と販売価格のペアから生成する
 */
class PluCode private (
    private val productId: ProductId,
    private val price: Price
) {
  override def toString: String = {
    productId.value.toString + "----" + price.toString
  }

  def decompose: (ProductId, Price) = (productId, price)
}

object PluCode {
  implicit lazy val pluCodeEq: Eq[PluCode] = (x: PluCode, y: PluCode) => {
    import cats.implicits.catsKernelStdOrderForString
    import cats.syntax.eq.catsSyntaxEq

    x.toString === y.toString
  }

  def generate(v: ProductId, a: Price): PluCode = {
    new PluCode(v, a)
  }

  def parse(v: String): PluCode = {
    val (productId, price) = v.split("""----""") match {
      case Array(a, b) => (a, b)
      case _           => throw new IllegalArgumentException()
    }

    new PluCode(ProductId(UUID.fromString(productId)), price.toInt)
  }
}
