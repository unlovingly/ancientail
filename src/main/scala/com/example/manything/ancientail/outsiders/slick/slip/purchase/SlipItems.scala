package com.example.manything.ancientail.outsiders.slick.slip.purchase

import slick.lifted.Tag

import com.example.manything.ambientendre.domain.models.product.ProductId
import com.example.manything.ancientail.domain.models.slip.{ SlipId, SlipItemId }
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class SlipItems(tag: Tag) extends Table[PolishedPurchaseSlipItem](tag, "purchase_slip_items") {
  import com.example.manything.ambientendre.outsiders.slick.product.productIdColumnType
  import com.example.manything.ancientail.outsiders.slick.slip.{
    slipIdColumnType,
    slipItemIdColumnType
  }

  def identity = column[SlipItemId]("slip_item_id", O.PrimaryKey, O.AutoInc)
  def productId = column[ProductId]("product_id", O.Unique)
  def amount = column[Int]("amount")
  def price = column[Int]("price")

  def slipId = column[SlipId]("slip_id")

  def * =
    (identity.?, productId, amount, price, slipId) <> ((PolishedPurchaseSlipItem.apply _).tupled, PolishedPurchaseSlipItem.unapply)
}
