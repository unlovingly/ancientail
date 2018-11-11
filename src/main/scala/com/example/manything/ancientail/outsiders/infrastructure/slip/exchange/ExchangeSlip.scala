package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.ancientail.outsiders.infrastructure.slip.SlipBase

/**
 * 移動伝票
 *
 * 店舗間で移動するとき発行される。
 * @param identity
 * @param senderId
 * @param receiverId
 */
case class ExchangeSlip(
  override val identity: Option[SlipId] = None,
  override val senderId: ShopId,
  override val receiverId: ShopId,
  override val publishedAt: OffsetDateTime
) extends SlipBase {
  type SenderIdType = ShopId
}
