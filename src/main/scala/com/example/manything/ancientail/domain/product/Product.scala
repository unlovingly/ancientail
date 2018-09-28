package com.example.manything.ancientail.domain.product

import com.example.manything.ambientendre.domain.publisher.{
  Publisher,
  PublisherId
}
import com.example.manything.roundelayout.domain.Entity

class Product(
  val identity: Option[ProductId] = None,
  val name: String,
  val publisher: Publisher
) extends Entity[ProductId] {}

object Product {
  def apply(id: Option[ProductId],
            name: String,
            publisher: Publisher): Product =
    new Product(id, name, publisher)
  def unapply(product: Product) =
    Some((product.identity, product.name, product.publisher))

  def rapply(id: Option[ProductId], name: String, publisherId: PublisherId) =
    new Product(id, name, Publisher(Some(publisherId), name))
  def runapply(product: Product) =
    Some(product.identity, product.name, product.publisher.identity.get)
}
