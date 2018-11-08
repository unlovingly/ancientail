package com.example.manything.ancientail.domain.slip

import com.example.manything.ancientail.domain.shop.ShopId

case class SellSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem]
) extends SlipBase {
  type SenderId = ShopId
}
