package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.shop._
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class Stocks(tag: Tag) extends Table[Stock](tag, "stocks") {
  import com.example.manything.ambientendre.outsiders.infrastructure.product.productIdColumnType

  def shopId = column[ShopId]("shop_id")
  def productId = column[ProductId]("product_id")
  def amount = column[Int]("name")
  def price = column[Int]("price")

  def shop =
    foreignKey("shop_fk", shopId, shops)(_.identity)

  def * =
    (shopId, productId, amount, price) <> (Stock.tupled, Stock.unapply)
}
