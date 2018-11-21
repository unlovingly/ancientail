package com.example.manything.ancientail.domain.slip

import java.time.ZonedDateTime
import java.util.UUID

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

trait SlipBase extends Entity {
  override type Identifier = SlipId
  type SenderIdType <: Identifiability[UUID]

  override val identity: Option[SlipId] = None

  /**
   * 伝票番号
   */
  val number: String

  /**
   * 発行者
   */
  val senderId: SenderIdType

  /**
   * 受領者
   */
  val receiverId: ShopId

  /**
   * 対象商品
   */
  val items: Seq[SlipItem]

  /**
   * 処理日
   */
  val approvedAt: ZonedDateTime

  /**
   * 発行日
   */
  val publishedAt: ZonedDateTime
}

case class SlipId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = SlipBase
}
