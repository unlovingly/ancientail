package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ancientail.domain.shop.ShopId
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class Shops(tag: Tag) extends Table[Shop](tag, "shops") {
  def identity = column[ShopId]("shop_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * =
    (identity.?, name) <> (Shop.tupled, Shop.unapply)
}

object Shops {
  implicit class ShopsExtensions[C[_]](q: Query[Shops, Shop, C]) {
    def withStocks =
      q.join(stocks)
        .on(_.identity === _.shopId)
  }
}
