package com.example.manything.ancientail.outsiders.infrastructure.slip

import java.time.{OffsetDateTime, ZoneId}

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId

/**
 * 伝票
 */
trait SlipBase {
  type EntityType
  type SenderIdType

  val identity: Option[SlipId] = None

  /**
   * 発行者
   */
  val senderId: SenderIdType

  /**
   * 受領者
   */
  val receiverId: ShopId

  /**
   * 処理日
   */
  val approvedAt: OffsetDateTime

  /**
   * 発行日
   */
  val publishedAt: OffsetDateTime

  def to(): EntityType
}
