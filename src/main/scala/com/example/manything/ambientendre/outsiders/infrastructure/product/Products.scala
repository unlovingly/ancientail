package com.example.manything.ambientendre.outsiders.infrastructure.product

import com.example.manything.ambientendre.domain.product.{Product, ProductId}
import com.example.manything.ambientendre.domain.publisher.PublisherId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Products(tag: Tag) extends Table[Product](tag, "products") {
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher._

  def identity = column[ProductId]("product_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def publisherId = column[PublisherId]("publisher_id")

  // https://stackoverflow.com/questions/15627981/mapped-projection-with-companion-object-in-slick
  def * =
    (identity.?, name, publisherId) <> (Product.tupled, Product.unapply)
}
