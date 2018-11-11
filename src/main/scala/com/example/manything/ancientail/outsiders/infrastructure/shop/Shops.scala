package com.example.manything.ancientail.outsiders.infrastructure.shop

import slick.lifted.Tag

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class Shops(tag: Tag) extends Table[Shop](tag, "shops") {
  def identity = column[ShopId]("shop_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * =
    (identity.?, name) <> ((Shop.apply _).tupled, Shop.unapply)
}
