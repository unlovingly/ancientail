package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import java.time.OffsetDateTime

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

/**
 * 購入伝票
 *
 * 販売者 (メーカー) が発行する伝票。納品書などに相当する
 * @param identity
 * @param senderId SenderId
 * @param receiverId ReceiverId
 */
case class PolishedPurchaseSlip(
  identity: Option[SlipId] = None,
  number: String,
  senderId: PublisherId,
  receiverId: ShopId,
  publishedAt: OffsetDateTime,
  approvedAt: OffsetDateTime
)
