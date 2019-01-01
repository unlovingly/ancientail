package com.example.manything.ancientail.outsiders.slick.slip.exchange

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip._
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlipItem

case class PolishedExchangeSlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price,
  slipId: SlipId
) {
  def to(): ExchangeSlipItem =
    ExchangeSlipItem(identity = identity,
                     productId = productId,
                     amount = amount,
                     price = price)
}

object PolishedExchangeSlipItem {
  def from(slipId: SlipId,
           entity: ExchangeSlipItem): PolishedExchangeSlipItem = {
    PolishedExchangeSlipItem(identity = entity.identity,
                             productId = entity.productId,
                             amount = entity.amount,
                             price = entity.price,
                             slipId = slipId)
  }
}
