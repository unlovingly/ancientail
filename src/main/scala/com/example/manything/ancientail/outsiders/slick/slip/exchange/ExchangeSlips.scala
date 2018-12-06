package com.example.manything.ancientail.outsiders.slick.slip.exchange

import java.time.OffsetDateTime

import slick.lifted.Tag

import com.example.manything.ancientail.domain.models.shop.ShopId
import com.example.manything.ancientail.domain.models.slip.SlipId
import com.example.manything.outsiders.infrastructure.PostgresProfile.api._

class ExchangeSlips(tag: Tag)
  extends Table[PolishedExchangeSlip](tag, "exchange_slips") {
  import com.example.manything.ancientail.outsiders.slick.slip.slipIdColumnType
  import com.example.manything.ancientail.outsiders.slick.shop.shopIdColumnType

  def identity = column[SlipId]("slip_id", O.PrimaryKey, O.AutoInc)
  def number = column[String]("number")
  def senderId = column[ShopId]("sender_id")
  def receiverId = column[ShopId]("receiver_id")
  def approvedAt = column[OffsetDateTime]("approved_at")
  def publishedAt = column[OffsetDateTime]("published_at")

  def * =
    (identity.?, number, senderId, receiverId, publishedAt, approvedAt) <> ((PolishedExchangeSlip.apply _).tupled, PolishedExchangeSlip.unapply)
}
