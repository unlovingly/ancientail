package com.example.manything.ancientail.outsiders.infrastructure.slip

import com.example.manything.ambientendre.domain.product.ProductId
import com.example.manything.ancientail.domain.slip._
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class SlipItems(tag: Tag) extends Table[SlipItem](tag, "slip_items") {
  import com.example.manything.ambientendre.outsiders.infrastructure.product._

  def identity = column[SlipItemId]("slip_item_id", O.PrimaryKey, O.AutoInc)
  def productId = column[ProductId]("product_id", O.Unique)
  def amount = column[Int]("amount")
  def price = column[Int]("price")

  def slipId = column[SlipId]("slip_id")

  def slip =
    foreignKey("slip_fk", slipId, slips)(_.identity)

  def * =
    (identity.?, productId, amount, price) <> (SlipItem.tupled, SlipItem.unapply)
}
