package com.example.manything.ancientail.domain.slip

import java.time.ZonedDateTime
import java.util.UUID

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

/**
 * 伝票
 *
 * @note 入出庫履歴を別途用意すべきかもしれない
 */
trait Slip extends Entity {
  override type Identifier = SlipId

  type SenderIdType <: Identifiability[UUID]

  val number: String
  val senderId: SenderIdType
  val receiverId: ShopId
  val items: Seq[SlipItem]
  val publishedAt: ZonedDateTime
  val approvedAt: ZonedDateTime
}

case class SlipId(value: UUID) extends Identifiability[UUID] {
  override type EntityType = Slip
}
