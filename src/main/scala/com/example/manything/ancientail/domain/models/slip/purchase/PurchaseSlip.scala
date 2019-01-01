package com.example.manything.ancientail.domain.models.slip.purchase

import java.time.{ ZoneId, ZonedDateTime }

import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip._

/**
 * 納品伝票
 *
 * @see com.example.manything.ancientail.domain.models.slip.Slip
 */
case class PurchaseSlip(
    override val identity: Option[SlipId] = None,
    override val number: String,
    override val senderId: PublisherId,
    override val receiverId: ShopId,
    override val items: Seq[PurchaseSlipItem],
    override val publishedAt: ZonedDateTime,
    override val approvedAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
) extends Slip {
  override type ReceiverIdType = ShopId
  override type SenderIdType = PublisherId
}
