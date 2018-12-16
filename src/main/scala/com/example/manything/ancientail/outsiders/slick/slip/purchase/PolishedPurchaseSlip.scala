package com.example.manything.ancientail.outsiders.slick.slip.purchase

import java.time.OffsetDateTime

import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.purchase.{
  PurchaseSlip,
  PurchaseSlipItem
}

/**
 * 購入伝票
 *
 * 販売者 (メーカー) が発行する伝票。納品書などに相当する
 */
case class PolishedPurchaseSlip(
  identity: Option[SlipId] = None,
  number: String,
  senderId: PublisherId,
  receiverId: ShopId,
  publishedAt: OffsetDateTime,
  approvedAt: OffsetDateTime
) {
  def to(items: Seq[PurchaseSlipItem] = Seq.empty): PurchaseSlip = {
    PurchaseSlip(
      identity = identity,
      number = number,
      senderId = senderId,
      receiverId = receiverId,
      publishedAt = publishedAt.toZonedDateTime,
      approvedAt = approvedAt.toZonedDateTime,
      items = items
    )
  }
}

object PolishedPurchaseSlip {
  def from(entity: PurchaseSlip): PolishedPurchaseSlip = {
    PolishedPurchaseSlip(
      identity = entity.identity,
      number = entity.number,
      senderId = entity.senderId,
      receiverId = entity.receiverId,
      publishedAt = entity.publishedAt.toOffsetDateTime,
      approvedAt = entity.approvedAt.toOffsetDateTime
    )
  }
}
