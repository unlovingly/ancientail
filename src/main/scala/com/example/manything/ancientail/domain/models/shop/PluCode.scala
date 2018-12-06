package com.example.manything.ancientail.domain.models.shop

import cats.Eq

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.models.slip.Price

case class PluCode(value: String)

object PluCode {
  implicit lazy val pluCodeEq: Eq[PluCode] = (x: PluCode, y: PluCode) => {
    import cats.implicits.catsKernelStdOrderForString
    import cats.syntax.eq.catsSyntaxEq

    x.value === y.value
  }

  def generate(v: ProductId, a: Price): PluCode = {
    // 本当は ShopId も含めたいが移動伝票の処理で困る
    new PluCode(v.value.toString + a.toString)
  }
}