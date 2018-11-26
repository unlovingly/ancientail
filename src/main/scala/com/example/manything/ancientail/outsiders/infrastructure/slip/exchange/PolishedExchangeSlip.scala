package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

/**
 * 移動伝票
 *
 * 店舗間で移動するとき発行される。
 */
case class PolishedExchangeSlip(
  identity: Option[SlipId] = None,
  number: String,
  senderId: ShopId,
  receiverId: ShopId,
  publishedAt: OffsetDateTime,
  approvedAt: OffsetDateTime
)
