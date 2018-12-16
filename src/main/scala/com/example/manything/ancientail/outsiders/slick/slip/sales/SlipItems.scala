package com.example.manything.ancientail.outsiders.slick.slip.sales

import slick.lifted.Tag

import com.example.manything.ancientail.domain.models.shop.PluCode
import com.example.manything.ancientail.domain.models.slip.{SlipId, SlipItemId}
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class SlipItems(tag: Tag)
  extends Table[PolishedSalesSlipItem](tag, "sales_slip_items") {
  import com.example.manything.ancientail.outsiders.slick.shop.pluCodeColumnType
  import com.example.manything.ancientail.outsiders.slick.slip.{
    slipIdColumnType,
    slipItemIdColumnType
  }

  def identity = column[SlipItemId]("slip_item_id", O.PrimaryKey, O.AutoInc)
  def pluCode = column[PluCode]("product_id", O.Unique)
  def amount = column[Int]("amount")
  def price = column[Int]("price")

  def slipId = column[SlipId]("slip_id")

  def * =
    (identity.?, pluCode, amount, price, slipId) <> ((PolishedSalesSlipItem.apply _).tupled, PolishedSalesSlipItem.unapply)
}
