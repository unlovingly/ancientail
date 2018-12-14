package com.example.manything.ancientail.domain.models.slip

import java.util.UUID

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

/**
 * 伝票の列…
 * @param identity Option[SlipItemId]
 * @param productId ProductId
 * @param amount 数量
 * @param price 仕入れ値
 */
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
