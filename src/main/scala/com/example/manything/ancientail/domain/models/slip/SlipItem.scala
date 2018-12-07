package com.example.manything.ancientail.domain.models.slip

import java.util.UUID

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class SlipItem(
  override val identity: Option[SlipItemId] = None,
  productId: ProductId,
  amount: Amount,
  price: Price
) extends Entity {
  override type Identifier = SlipItemId
}

case class SlipItemId(override val value: UUID)
  extends Identifiability[SlipItem, UUID]
