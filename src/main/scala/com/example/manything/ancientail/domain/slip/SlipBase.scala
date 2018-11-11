package com.example.manything.ancientail.domain.slip

import java.time.{ZoneId, ZonedDateTime}
import java.util.UUID

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

trait SlipBase extends Entity {
  override type Identifier = SlipId
  type SenderIdType

  override val identity: Option[SlipId] = None

  /**
   * 発行者
   */
  val senderId: SenderIdType

  /**
   * 受領者
   */
  val receiverId: ShopId
  val items: Seq[SlipItem]

  /**
   * 処理日
   */
  val approvedAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))

  /**
   * 発行日
   */
  val publishedAt: ZonedDateTime
}

case class SlipId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = SlipBase
}
