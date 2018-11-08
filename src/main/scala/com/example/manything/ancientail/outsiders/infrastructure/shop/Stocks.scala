package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.shop._
import com.example.manything.ancientail.domain.slip.{Amount, Price}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class Stocks(tag: Tag) extends Table[Stock](tag, "stocks") {
  import com.example.manything.ambientendre.outsiders.infrastructure.product._

  def pluCode = column[PluCode]("plu_code", O.PrimaryKey)
  // https://github.com/slick/slick/issues/966#issuecomment-379232820
  def shopId = column[ShopId]("shop_id")
  def productId = column[ProductId]("product_id")
  def amount = column[Amount]("amount")
  def price = column[Price]("price")

  def * =
    (pluCode, shopId, productId, amount, price) <> ((Stock.apply _).tupled, Stock.unapply)
}