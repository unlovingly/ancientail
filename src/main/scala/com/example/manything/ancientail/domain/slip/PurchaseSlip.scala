package com.example.manything.ancientail.domain.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId

case class PurchaseSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: PublisherId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem]
) extends SlipBase {
  type SenderId = PublisherId
}
