package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

abstract class SlipsBase[EntityType](tag: Tag, _tableName: String)
  extends Table[EntityType](tag, _tableName) {
  import com.example.manything.ancientail.outsiders.infrastructure.shop._

  def identity = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def receiverId = column[ShopId]("shop_id")
}