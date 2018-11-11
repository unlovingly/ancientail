package com.example.manything.ancientail.outsiders.infrastructure.slip.purchase

import java.time.{OffsetDateTime, ZoneId}

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipBase

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
  override val identity: Option[SlipId] = None,
  override val senderId: PublisherId,
  override val receiverId: ShopId
) extends SlipBase {
  type SenderIdType = PublisherId
}
