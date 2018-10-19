package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip._
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

class SlipItems(tag: Tag) extends Table[SlipItem](tag, "slip_items") {
  import com.example.manything.ambientendre.outsiders.infrastructure.product._

  def identity = column[SlipItemId]("slip_item_id", O.PrimaryKey, O.AutoInc)
  def productId = column[ProductId]("product_id")
  def amount = column[Int]("publisher_id")
  def price = column[Int]("item_id")

  def slipId = column[SlipId]("slip_id")

  def slip =
    foreignKey("slip_fk", slipId, slips)(_.identity)

  def * =
    (identity.?, productId, amount, price) <> (SlipItem.tupled, SlipItem.unapply)
}
