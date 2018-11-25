package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import java.time.OffsetDateTime

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

/**
 * 売上伝票
 *
 * 顧客への販売時に店舗が発行する。一人に対して
 * @param identity
 * @param senderId SenderId 販売店
 * @param receiverId ReceiverId 正確には受領者だが販売店とする
 */
case class PolishedSalesSlip(
  identity: Option[SlipId] = None,
  number: String,
  senderId: ShopId,
  receiverId: ShopId,
  publishedAt: OffsetDateTime,
  approvedAt: OffsetDateTime
)
