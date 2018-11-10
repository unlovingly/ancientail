package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

trait SlipBase extends Entity {
  override type Identifier = SlipId
  type SenderId

  override val identity: Option[SlipId] = None
  val senderId: SenderId
  val receiverId: ShopId
  val items: Seq[SlipItem]
}

case class SlipId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = SlipBase
}
