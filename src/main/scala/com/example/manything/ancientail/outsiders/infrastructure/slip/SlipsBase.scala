package com.example.manything.ancientail.outsiders.infrastructure.slip

import java.time.OffsetDateTime

import slick.lifted.Tag

import com.example.manything.ancientail.domain.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

abstract class SlipsBase[EntityType](tag: Tag, _tableName: String)
  extends Table[EntityType](tag, _tableName) {
  import com.example.manything.ancientail.outsiders.infrastructure.shop._

  def identity = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def number = column[String]("number")
  def receiverId = column[ShopId]("shop_id")
  def approvedAt = column[OffsetDateTime]("approved_at")
  def publishedAt = column[OffsetDateTime]("published_at")
}
