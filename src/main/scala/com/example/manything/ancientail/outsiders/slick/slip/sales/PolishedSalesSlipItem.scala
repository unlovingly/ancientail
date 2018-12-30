package com.example.manything.ancientail.outsiders.slick.slip.sales

import com.example.manything.ancientail.domain.models.shop.PluCode
import com.example.manything.ancientail.domain.models.slip._
import com.example.manything.ancientail.domain.models.slip.sales.SalesSlipItem

case class PolishedSalesSlipItem(
  identity: Option[SlipItemId] = None,
  pluCode: PluCode,
  amount: Amount,
  price: Price,
  slipId: SlipId
) {
  def to(): SalesSlipItem =
    SalesSlipItem(identity = identity,
                  pluCode = pluCode,
                  amount = amount,
                  price = price)
}

object PolishedSalesSlipItem {
  def from(slipId: SlipId, entity: SalesSlipItem): PolishedSalesSlipItem = {
    PolishedSalesSlipItem(identity = entity.identity,
                          pluCode = entity.pluCode,
                          amount = entity.amount,
                          price = entity.price,
                          slipId = slipId)
  }
}
