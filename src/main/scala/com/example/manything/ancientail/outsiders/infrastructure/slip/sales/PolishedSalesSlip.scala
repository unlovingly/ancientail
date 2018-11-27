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
 *
 * @param identity
 * @param senderId SenderId 販売店
 * @param receiverId ReceiverId 正確には受領者だが販売店とする
 */
case class PolishedSalesSlip(
  identity: Option[SlipId] = None,
  number: String,
  senderId: ShopId,
  receiverId: CustomerId,
  publishedAt: OffsetDateTime,
  approvedAt: OffsetDateTime
)
