package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class Slip(
  identity: Option[SlipId] = None,
  senderId: PublisherId,
  receiverId: ShopId,
  items: Seq[SlipItem]
) extends Entity {
  override type Identifier = SlipId
}

case class SlipId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = Slip
}
