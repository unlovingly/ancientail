package com.example.manything.ancientail.domain.slip.sales

import java.time.ZonedDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip._

/**
 * 売上伝票
 * @param identity
 * @param senderId
 * @param receiverId
 * @param items
 * @param publishedAt ZonedDateTime 発行日
 */
case class SalesSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val items: Seq[SlipItem],
  override val publishedAt: ZonedDateTime
) extends SlipBase {
  type SenderIdType = ShopId
}
