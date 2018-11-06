package com.example.manything.ancientail.domain.slip

import com.example.manything.ancientail.domain.shop.ShopId

case class ExchangeSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem]
) extends Slip[ShopId](
    identity = identity,
    senderId = senderId,
    receiverId = receiverId,
    items = items
  ) {
  def copy(identity: Option[SlipId] = identity,
           senderId: ShopId = senderId,
           receiverId: ShopId = receiverId,
           items: Seq[SlipItem] = items): ExchangeSlip = ???
}
