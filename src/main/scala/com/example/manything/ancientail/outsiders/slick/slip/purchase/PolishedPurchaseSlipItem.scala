package com.example.manything.ancientail.outsiders.slick.slip.purchase

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip._
import com.example.manything.ancientail.domain.models.slip.purchase.PurchaseSlipItem

case class PolishedPurchaseSlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price,
  slipId: SlipId
) {
  def to(): PurchaseSlipItem =
    PurchaseSlipItem(identity = identity,
                     productId = productId,
                     amount = amount,
                     price = price)
}

object PolishedPurchaseSlipItem {
  def from(slipId: SlipId,
           entity: PurchaseSlipItem): PolishedPurchaseSlipItem = {
    PolishedPurchaseSlipItem(identity = entity.identity,
                             productId = entity.productId,
                             amount = entity.amount,
                             price = entity.price,
                             slipId = slipId)
  }
}
