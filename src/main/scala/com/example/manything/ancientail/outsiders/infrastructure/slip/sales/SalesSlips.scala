package com.example.manything.ancientail.outsiders.infrastructure.slip.sales

import java.time.OffsetDateTime

import slick.lifted.Tag

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.slip.SlipId
import com.example.manything.blessedict.domain.models.customer.CustomerId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class SalesSlips(tag: Tag)
  extends Table[PolishedSalesSlip](tag, "sales_slips") {
  import com.example.manything.ancientail.outsiders.infrastructure.shop.shopIdColumnType
  import com.example.manything.ancientail.outsiders.infrastructure.slip.slipIdColumnType
  import com.example.manything.blessedict.outsiders.slick.customerIdColumnType

  def identity = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def number = column[String]("number")
  def senderId = column[ShopId]("sender_id")
  def receiverId = column[Option[CustomerId]]("receiver_id")
  def approvedAt = column[OffsetDateTime]("approved_at")
  def publishedAt = column[OffsetDateTime]("published_at")

  def * =
    (identity.?, number, senderId, receiverId, publishedAt, approvedAt) <> ((PolishedSalesSlip.apply _).tupled, PolishedSalesSlip.unapply)
}
