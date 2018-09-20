package com.example.manything.ancientail.outsiders.infrastructure.product

import com.example.manything.ancientail.domain.product.{Product, ProductId}
import slick.jdbc.PostgresProfile.api._

class Products(tag: Tag) extends Table[Product](tag, "merchandises") {
  def identity = column[ProductId]("id", O.PrimaryKey)
  def name = column[String]("name")
  // def publisher = column[Publisher]("publisher")
  def * = (identity.?, name) <> (Product.tupled, Product.unapply)
}
