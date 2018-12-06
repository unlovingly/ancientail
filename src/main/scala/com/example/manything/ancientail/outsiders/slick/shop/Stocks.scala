package com.example.manything.ancientail.outsiders.slick.shop

import slick.lifted.Tag

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.models.shop._
import com.example.manything.ancientail.domain.models.slip.{Amount, Price}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Stocks(tag: Tag) extends Table[Stock](tag, "stocks") {
  import com.example.manything.ambientendre.outsiders.infrastructure.product.productIdColumnType

  def pluCode = column[PluCode]("plu_code", O.PrimaryKey, O.AutoInc)
  def shopId = column[ShopId]("shop_id")
  def productId = column[ProductId]("product_id")
  def amount = column[Amount]("amount")
  def price = column[Price]("price")

  def * =
    (pluCode, shopId, productId, amount, price) <> ((Stock.apply _).tupled, Stock.unapply)
}
