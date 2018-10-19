package com.example.manything.ancientail.outsiders.infrastructure.shop

import com.example.manything.ancientail.domain.shop.{Shop, ShopId}
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

class Shops(tag: Tag) extends Table[Shop](tag, "shops") {
  def identity = column[ShopId]("name_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * =
    (identity.?, name) <> (Shop.tupled, Shop.unapply)
}
