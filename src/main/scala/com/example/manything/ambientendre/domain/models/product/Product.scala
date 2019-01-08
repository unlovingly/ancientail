package com.example.manything.ambientendre.domain.models.product

import java.util.UUID

import com.example.manything.ambientendre.domain.models.publisher.PublisherId
import com.example.manything.roundelayout.domain.{ Entity, Identifiability }

/**
 * 製品情報
 *
 * @param identity Option[ProductId] サロゲートキー
 * @param name String 型番
 * @param publisherId PublisherId 販売者
 */
case class Product(
    override val identity: Option[ProductId] = None,
    name: String,
    publisherId: PublisherId
) extends Entity {
  override type Identifier = ProductId
}

case class ProductId(override val value: UUID) extends Identifiability[Product, UUID]
