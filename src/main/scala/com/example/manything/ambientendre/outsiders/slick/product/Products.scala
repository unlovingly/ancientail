package com.example.manything.ambientendre.outsiders.slick.product

import com.example.manything.ambientendre.domain.product.{Product, ProductId}
import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Products(tag: Tag) extends Table[Product](tag, "products") {
  import com.example.manything.ambientendre.outsiders.slick.publisher.publisherIdColumnType

  def identity = column[ProductId]("product_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def publisherId = column[PublisherId]("publisher_id")

  def * =
    (identity.?, name, publisherId) <> (Product.tupled, Product.unapply)
}
