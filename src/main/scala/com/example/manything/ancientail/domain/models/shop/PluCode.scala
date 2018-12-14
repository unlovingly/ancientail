package com.example.manything.ancientail.domain.models.shop

import cats.Eq

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip.Price

/**
 * Price Look Up Code, 製品情報と販売価格のペアから生成する
 * @param value String JAN などは 11 桁
 */
case class PluCode(value: String)

object PluCode {
  implicit lazy val pluCodeEq: Eq[PluCode] = (x: PluCode, y: PluCode) => {
    import cats.implicits.catsKernelStdOrderForString
    import cats.syntax.eq.catsSyntaxEq

    x.value === y.value
  }

  def generate(v: ProductId, a: Price): PluCode = {
    new PluCode(v.value.toString + a.toString)
  }
}
