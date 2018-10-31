package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.shop._
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class Stocks(tag: Tag) extends Table[Stock](tag, "stocks") {
  import com.example.manything.ambientendre.outsiders.infrastructure.product._

  // https://github.com/slick/slick/issues/966#issuecomment-379232820
  def shopId = column[ShopId]("shop_id", O.PrimaryKey)
  def productId = column[ProductId]("product_id", O.PrimaryKey)
  def amount = column[Int]("amount")
  def price = column[Int]("price", O.PrimaryKey)

  def pk = primaryKey("pk_stocks", (shopId, productId, price))

  def shop =
    foreignKey("shop_fk", shopId, shops)(_.identity)

  def product =
    foreignKey("product_fk", productId, products)(_.identity)

  def * =
    (shopId, productId, amount, price) <> (Stock.tupled, Stock.unapply)
}
