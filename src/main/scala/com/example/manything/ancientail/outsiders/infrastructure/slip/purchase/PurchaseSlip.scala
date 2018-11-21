package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import java.time.OffsetDateTime

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip
import com.example.manything.ancientail.domain.slip.purchase.{
  PurchaseSlip => Entity
}
import com.example.manything.ancientail.outsiders.infrastructure.slip.{
  SlipBase,
  SlipObject
}

/**
 * 購入伝票
 *
 * 販売者 (メーカー) が発行する伝票。納品書などに相当する
 * @param identity
 * @param senderId SenderId
 * @param receiverId ReceiverId
 * @see SlipBase
 */
case class PurchaseSlip(
  override val identity: Option[slip.SlipId] = None,
  override val number: String,
  override val senderId: PublisherId,
  override val receiverId: ShopId,
  override val publishedAt: OffsetDateTime,
  override val approvedAt: OffsetDateTime
) extends SlipBase {
  type SenderIdType = PublisherId
  override type EntityType = Entity

  override def to(items: Seq[slip.SlipItem] = Seq.empty): EntityType =
    Entity.apply(
      identity = identity,
      number = number,
      senderId = senderId,
      receiverId = receiverId,
      items = items,
      publishedAt = publishedAt.toZonedDateTime,
      approvedAt = approvedAt.toZonedDateTime
    )
}

object PurchaseSlip extends SlipObject[PurchaseSlip] {
  override def from(e: slip.SlipBase): PurchaseSlip =
    PurchaseSlip(
      identity = e.identity,
      number = e.number,
      senderId = PublisherId(e.senderId.value), // FIXME
      receiverId = e.receiverId,
      publishedAt = e.publishedAt.toOffsetDateTime,
      approvedAt = e.approvedAt.toOffsetDateTime
    )
}
