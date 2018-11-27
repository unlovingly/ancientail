package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.sales.SalesSlip
import com.example.manything.ancientail.domain.slip.{SlipId, SlipItem}
import com.example.manything.blessedict.domain.customer.CustomerId

/**
 * 売上伝票
 *
 * 顧客への販売時に店舗が発行する。一人に対して
 */
case class PolishedSalesSlip(
  identity: Option[SlipId] = None,
  number: String,
  senderId: ShopId,
  receiverId: CustomerId,
  publishedAt: OffsetDateTime,
  approvedAt: OffsetDateTime
) {
  def to(items: Seq[SlipItem] = Seq.empty): SalesSlip = {
    SalesSlip(
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

object PolishedSalesSlip {
  def from(entity: SalesSlip): PolishedSalesSlip = {
    PolishedSalesSlip(
      identity = entity.identity,
      number = entity.number,
      senderId = entity.senderId,
      receiverId = entity.receiverId,
      publishedAt = entity.publishedAt.toOffsetDateTime,
      approvedAt = entity.approvedAt.toOffsetDateTime
    )
  }
}
