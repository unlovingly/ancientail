package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

case class PurchaseSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: PublisherId,
  override val receiverId: ShopId
) extends SlipBase {
  type SenderId = PublisherId
}
