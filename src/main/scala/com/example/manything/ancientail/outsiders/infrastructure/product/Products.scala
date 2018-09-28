package com.example.manything.ancientail.outsiders.infrastructure.product

import com.example.manything.ancientail.domain.product.{Product, ProductId}
import slick.jdbc.PostgresProfile.api._

class Products(tag: Tag) extends Table[Product](tag, "products") {
  import com.example.manything.ambientendre.domain.publisher._
  import com.example.manything.ambientendre.outsiders.infrastructure.publisher._

  def identity = column[ProductId]("product_id", O.PrimaryKey)
  def name = column[String]("name")
  def publisherId = column[PublisherId]("publisher_id")
  def publisher =
    foreignKey("publisher_fk", publisherId, publishers)(_.identity)

  // https://stackoverflow.com/questions/15627981/mapped-projection-with-companion-object-in-slick
  def * =
    //(identity.?, name, publisherId) <> (Product.tupled, Product.unapply)
    (identity.?, name, publisherId) <> ((Product.rapply _).tupled, Product.runapply)
}
