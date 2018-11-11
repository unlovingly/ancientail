package com.example.manything.ancientail.domain.slip.purchase

import java.time.ZonedDateTime

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._

/**
 * 納品伝票
 * @param identity
 * @param senderId
 * @param receiverId
 * @param items
 * @param publishedAt
 */
case class PurchaseSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: PublisherId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem],
  override val publishedAt: ZonedDateTime
) extends SlipBase {
  type SenderIdType = PublisherId
}
