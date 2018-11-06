package com.example.manything.ancientail.domain.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId

case class PurchaseSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: PublisherId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem]
) extends Slip[PublisherId](
    identity = identity,
    senderId = senderId,
    receiverId = receiverId,
    items = items
  ) {
  def copy(identity: Option[SlipId] = identity,
           senderId: PublisherId = senderId,
           receiverId: ShopId = receiverId,
           items: Seq[SlipItem] = items): PurchaseSlip = ???
}
