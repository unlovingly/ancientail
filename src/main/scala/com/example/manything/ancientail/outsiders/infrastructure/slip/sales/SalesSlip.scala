package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip
import com.example.manything.ancientail.domain.slip.sales.{SalesSlip => Entity}
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipItem => EntityItem
}
import com.example.manything.ancientail.outsiders.infrastructure.slip.{
  SlipBase,
  SlipObject
}

/**
 * 売上伝票
 *
 * 顧客への販売時に店舗が発行する。一人に対して
 * @param identity
 * @param senderId SenderId 販売店
 * @param receiverId ReceiverId 正確には受領者だが販売店とする
 * @see SlipBase
 */
case class SalesSlip(
  override val identity: Option[SlipId] = None,
  override val number: String,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val publishedAt: OffsetDateTime,
  override val approvedAt: OffsetDateTime
) extends SlipBase {
  type SenderIdType = ShopId
  override type EntityType = Entity

  override def to(items: Seq[EntityItem] = Seq.empty): EntityType =
    Entity.apply(
      identity = identity,
      number = number,
      senderId = senderId,
      receiverId = receiverId,
      items = Seq.empty,
      publishedAt = publishedAt.toZonedDateTime,
      approvedAt = approvedAt.toZonedDateTime
    )
}

object SalesSlip extends SlipObject[SalesSlip] {
  override def from(e: slip.SlipBase): SalesSlip =
    SalesSlip(
      identity = e.identity,
      number = e.number,
      senderId = ShopId(e.senderId.value), // FIXME
      receiverId = e.receiverId,
      publishedAt = e.publishedAt.toOffsetDateTime,
      approvedAt = e.approvedAt.toOffsetDateTime
    )
}
