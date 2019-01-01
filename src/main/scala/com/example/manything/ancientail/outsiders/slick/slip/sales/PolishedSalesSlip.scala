package com.example.manything.ancientail.outsiders.slick.slip.sales

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.ancientail.domain.models.slip.sales.{ SalesSlip, SalesSlipItem }
import com.example.manything.blessedict.domain.models.customer.CustomerId

/**
 * 売上伝票
 *
 * 顧客への販売時に店舗が発行する。一人に対して
 */
case class PolishedSalesSlip(
    identity: Option[SlipId] = None,
    number: String,
    senderId: ShopId,
    receiverId: Option[CustomerId],
    publishedAt: OffsetDateTime,
    approvedAt: OffsetDateTime
) {
  def to(items: Seq[SalesSlipItem] = Seq.empty): SalesSlip = {
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
      number = entity.senderId.toString + entity.publishedAt.toString,
      senderId = entity.senderId,
      receiverId = entity.receiverId,
      publishedAt = entity.publishedAt.toOffsetDateTime,
      approvedAt = entity.approvedAt.toOffsetDateTime
    )
  }
}
