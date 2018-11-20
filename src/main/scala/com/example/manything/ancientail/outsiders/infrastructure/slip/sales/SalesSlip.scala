package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.{
  SlipId,
  SlipItem => EntityItem
}
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipBase
import com.example.manything.ancientail.domain.slip.sales.{SalesSlip => Entity}

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
      senderId = senderId,
      receiverId = receiverId,
      items = Seq.empty,
      publishedAt = publishedAt.toZonedDateTime,
      approvedAt = approvedAt.toZonedDateTime
    )
}
