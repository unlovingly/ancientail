package com.example.manything.ancientail.domain.models.slip

import java.util.UUID

import com.example.manything.roundelayout.domain.{Entity, Identifiability}

/**
 * 伝票の列…
 */
trait SlipItem extends Entity {
  override type Identifier = SlipItemId
  override val identity: Option[SlipItemId] = None
  val amount: Amount
  val price: Price
}

case class SlipItemId(override val value: UUID)
  extends Identifiability[SlipItem, UUID]
