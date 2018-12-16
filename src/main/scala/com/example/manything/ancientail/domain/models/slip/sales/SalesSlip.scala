package com.example.manything.ancientail.domain.models.slip.sales

import java.time.{ZoneId, ZonedDateTime}

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip._
import com.example.manything.blessedict.domain.models.customer.CustomerId

/**
 * 売上伝票
 * @see com.example.manything.ancientail.domain.models.slip.Slip
 */
case class SalesSlip(
  override val identity: Option[SlipId] = None,
  override val number: String,
  override val senderId: ShopId,
  override val receiverId: Option[CustomerId],
  override val items: Seq[SalesSlipItem],
  override val publishedAt: ZonedDateTime,
  override val approvedAt: ZonedDateTime =
    ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
) extends Slip {
  override type ReceiverIdType = Option[CustomerId]
  override type SenderIdType = ShopId
}
