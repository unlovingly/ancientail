package com.example.manything.ambientendre.domain.product

import java.util.UUID

import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.roundelayout.domain.{Entity, Identifiability}

case class Product(
  override val identity: Option[ProductId] = None,
  name: String,
  publisherId: PublisherId
) extends Entity {
  override type Identifier = ProductId
}

case class ProductId(override val value: UUID)
  extends Identifiability[Product, UUID]
