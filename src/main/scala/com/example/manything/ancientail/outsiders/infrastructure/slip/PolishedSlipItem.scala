package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip._

case class PolishedSlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price,
  slipId: SlipId
) {
  def to(): SlipItem =
    SlipItem(identity = identity,
             productId = productId,
             amount = amount,
             price = price)
}

object PolishedSlipItem {
  def from(slipId: SlipId, entity: SlipItem): PolishedSlipItem = {
    PolishedSlipItem(identity = entity.identity,
                     productId = entity.productId,
                     amount = entity.amount,
                     price = entity.price,
                     slipId = slipId)
  }
}
