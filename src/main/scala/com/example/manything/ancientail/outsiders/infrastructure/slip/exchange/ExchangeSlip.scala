package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipBase

case class ExchangeSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId
) extends SlipBase {
  type SenderId = ShopId
}