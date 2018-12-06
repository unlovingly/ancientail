package com.example.manything.ancientail.outsiders.slick.shop

import slick.lifted.Tag

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Shops(tag: Tag) extends Table[PolishedShop](tag, "shops") {
  def identity = column[ShopId]("shop_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * =
    (identity.?, name) <> ((PolishedShop.apply _).tupled, PolishedShop.unapply)
}
