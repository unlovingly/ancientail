package com.example.manything.ancientail.domain.slip.exchange

import java.time.ZonedDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._

/**
 * 移動伝票 (入庫伝票、出庫伝票)
 * @param identity
 * @param senderId
 * @param receiverId
 * @param items
 * @param publishedAt
 */
case class ExchangeSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem],
  override val publishedAt: ZonedDateTime
) extends SlipBase {
  type SenderIdType = ShopId
}