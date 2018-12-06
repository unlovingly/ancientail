package com.example.manything.ancientail.outsiders.infrastructure.slip.exchange

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.exchange.ExchangeSlip
import com.example.manything.ancientail.domain.models.slip.{SlipId, SlipItem}

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
) {
  def to(items: Seq[SlipItem] = Seq.empty): ExchangeSlip = {
    ExchangeSlip(
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

object PolishedExchangeSlip {
  def from(entity: ExchangeSlip): PolishedExchangeSlip = {
    PolishedExchangeSlip(
      identity = entity.identity,
      number = entity.number,
      senderId = entity.senderId,
      receiverId = entity.receiverId,
      publishedAt = entity.publishedAt.toOffsetDateTime,
      approvedAt = entity.approvedAt.toOffsetDateTime
    )
  }
}
