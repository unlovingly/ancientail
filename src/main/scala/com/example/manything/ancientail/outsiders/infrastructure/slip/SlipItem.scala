package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip._
import com.example.manything.ancientail.domain.slip.{SlipItem => EntityItem}

case class SlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price,
  slipId: SlipId
) {
  def to(): EntityItem =
    EntityItem(identity = identity,
               productId = productId,
               amount = amount,
               price = price)
}
