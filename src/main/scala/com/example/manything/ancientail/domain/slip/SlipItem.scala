package com.example.manything.ancientail.domain.slip

import java.util.UUID

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class SlipItem(
  identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price
) extends Entity {
  override type Identifier = SlipItemId
}

case class SlipItemId(override val value: UUID) extends Identifiability[UUID] {
  override type EntityType = SlipItem
}
