package com.example.manything.ancientail.domain.slip.exchange
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._

case class ExchangeSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem]
) extends SlipBase {
  type SenderId = ShopId
}
