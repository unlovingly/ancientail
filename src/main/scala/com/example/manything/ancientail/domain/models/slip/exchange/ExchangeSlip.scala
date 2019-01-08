package com.example.manything.ancientail.domain.models.slip.exchange

import java.time.{ ZoneId, ZonedDateTime }

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip._

/**
 * 移動伝票 (入庫伝票、出庫伝票)
 *
 * @see com.example.manything.ancientail.domain.models.slip.Slip
 */
case class ExchangeSlip(
    override val identity: Option[SlipId] = None,
    override val number: String,
    override val senderId: ShopId,
    override val receiverId: ShopId,
    override val items: Seq[ExchangeSlipItem],
    override val publishedAt: ZonedDateTime,
    override val approvedAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
) extends Slip {
  override type ReceiverIdType = ShopId
  override type SenderIdType = ShopId
}
